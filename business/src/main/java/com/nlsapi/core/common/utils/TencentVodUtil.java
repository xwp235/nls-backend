package com.nlsapi.core.common.utils;

import cn.hutool.json.JSONObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicSessionCredentials;
import com.qcloud.cos.region.Region;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

/**
 * 腾讯云VOD上传SDK包下载
 * https://cloud.tencent.com/document/product/266/10276
 */
//@Component
public class TencentVodUtil {

    private static String accessKeyId;

    private static String accessKeySecret;

    @Value("${vod.tencent.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        TencentVodUtil.accessKeyId = accessKeyId;
    }

    @Value("${vod.tencent.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        TencentVodUtil.accessKeySecret = accessKeySecret;
    }

    private static BigDecimal fileTransAudioPrice;

    private static String regionId = "ap-chongqing";
    private static Long appId = 1253747199L;

    @Value("${vod.aliyun.filetrans.audio.price}")
    public void setFileTransAudioPrice(BigDecimal fileTransAudioPrice) {
        TencentVodUtil.fileTransAudioPrice = fileTransAudioPrice;
    }

    /**
     * 使用AK初始化VOD客户端
     */
    public static VodUploadClient initVodClient() {
        // 点播服务接入区域
        // https://cloud.tencent.com/document/api/266/31756#.E5.9C.B0.E5.9F.9F.E5.88.97.E8.A1.A8
        return new VodUploadClient(accessKeyId, accessKeySecret);
    }

    /**
     * 获取视频上传地址和凭证
     * @param vodClient
     */
    public static VodUploadResponse createUploadVideo(VodUploadClient vodClient, String fileName) throws Exception {
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath(fileName);
        request.setMediaName(fileName);
        // request.setDescription("this is desc");
        // request.setTags("tag1,tag2");
        // request.setCoverURL("http://vod.aliyun.com/test_cover_url.jpg");
        // request.setCateId(1000115308L);

        // 这段逻辑放到付费成功后处理
        // // 如果是视频，则增加转码任务：提取音频
        // String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        // if ("mp4".equals(suffix) || "mov".equals(suffix)) {
        //     log.info("上传的是视频，需要添加提取音频和标清视频转码模板组ID：{}", audioTemplateGroupId);
        //     request.setTemplateGroupId(audioTemplateGroupId);
        // }

        //request.setWorkflowId("");
        //request.setStorageLocation("");
        //request.setAppId("app-1000000");
        request.setSubAppId(appId);
        return vodClient.upload(regionId,request,1000);
    }

    /**
     * 使用上传凭证和地址初始化COS客户端（注意需要先Base64解码并Json Decode再传入）
     * @param uploadAuth
     * @return
     */
    public static COSClient initCosClient(JSONObject uploadAuth) {
        var credentials = new BasicSessionCredentials(uploadAuth.getStr("AccessKeyId"),
                uploadAuth.getStr("AccessKeySecret"),uploadAuth.getStr("SecurityToken"));

        //初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        //设置bucket所在的区域，华南：gz 华北：tj 华东：sh
        clientConfig.setRegion(new Region(regionId));

        return new COSClient(credentials,clientConfig);
    }

//    /**
//     * 简单上传
//     * @param ossClient
//     * @param uploadAddress
//     * @param inputStream
//     */
//    public static void uploadLocalFile(OSSClient ossClient, JSONObject uploadAddress, InputStream inputStream){
//        String bucketName = uploadAddress.getStr("Bucket");
//        String objectName = uploadAddress.getStr("FileName");
//        // 单文件上传
//        ossClient.putObject(bucketName, objectName, inputStream);
//
//        /* 视频点播不支持追加上传
//        // 追加上传
//        ObjectMetadata meta = new ObjectMetadata();
//        meta.setContentType("text/plain");
//        AppendObjectRequest request = new AppendObjectRequest(bucketName, objectName, file, meta);
//        request.setPosition(0L);
//        ossClient.appendObject(request);*/
//    }
//
//    /**
//     * 上传本地文件
//     */
//    public static UploadVideoResponse uploadLocalFile(String title, String fileFullPath) {
//        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileFullPath);
//        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
//        request.setPartSize(2 * 1024 * 1024L);
//        /* 可指定分片上传时的并发线程数，默认为1，（注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
//        request.setTaskNum(1);
//    /* 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
//    注意：断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
//        //request.setEnableCheckpoint(false);
//        /* OSS慢请求日志打印超时时间，是指每个分片上传时间超过该阈值时会打印debug日志，如果想屏蔽此日志，请调整该阈值。单位：毫秒，默认为300000毫秒*/
//        //request.setSlowRequestsThreshold(300000L);
//        /* 可指定每个分片慢请求时打印日志的时间阈值，默认为300s*/
//        //request.setSlowRequestsThreshold(300000L);
//        /* 是否显示水印（可选），指定模板组ID时，根据模板组配置确定是否显示水印*/
//        //request.setIsShowWaterMark(true);
//        /* 自定义消息回调设置及上传加速设置（可选）, Extend为自定义扩展设置，MessageCallback为消息回调设置，AccelerateConfig为上传加速设置（上传加速功能需要先申请开通后才能使用）*/
//        //request.setUserData("{\"Extend\":{\"test\":\"www\",\"localId\":\"xxxx\"},\"MessageCallback\":{\"CallbackType\":\"http\",\"CallbackURL\":\"http://example.aliyundoc.com\"},\"AccelerateConfig\":{\"Type\":\"oss\",\"Domain\":\"****Bucket.oss-accelerate.aliyuncs.com\"}}");
//        /* 视频分类ID（可选） */
//        //request.setCateId(0);
//        /* 视频标签，多个用逗号分隔（可选） */
//        //request.setTags("标签1,标签2");
//        /* 视频描述（可选）*/
//        //request.setDescription("视频描述");
//        /* 封面图片（可选）*/
//        //request.setCoverURL("http://cover.example.com/image_01.jpg");
//        /* 模板组ID（可选）*/
//        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e5****");
//        /* 工作流ID（可选）*/
//        //request.setWorkflowId("d4430d07361f0*be1339577859b0****");
//        /* 存储区域（可选）*/
//        //request.setStorageLocation("in-201703232118266-5sejd****.oss-cn-shanghai.aliyuncs.com");
//        /* 开启默认上传进度回调 */
//        //request.setPrintProgress(false);
//        /* 设置自定义上传进度回调（必须继承 VoDProgressListener）*/
//        /*默认关闭。如果开启了这个功能，上传过程中服务端会在日志中返回上传详情。如果不需要接收此消息，需关闭此功能*/
//        //request.setProgressListener(new PutObjectProgressListener());
//        /* 设置您实现的生成STS信息的接口实现类*/
//        // request.setVoDRefreshSTSTokenListener(new RefreshSTSTokenImpl());
//        /* 设置应用ID*/
//        //request.setAppId("app-100****");
//        /* 点播服务接入点 */
//        //request.setApiRegionId("cn-shanghai");
//        /* ECS部署区域*/
//        // request.setEcsRegionId("cn-shanghai");
//
//        /* 配置代理访问（可选） */
//        //OSSConfig ossConfig = new OSSConfig();
//        /* <必填>设置代理服务器主机地址 */
//        //ossConfig.setProxyHost("<yourProxyHost>");
//        /* <必填>设置代理服务器端口 */
//        //ossConfig.setProxyPort(-1);
//        /* 设置连接OSS所使用的协议（HTTP或HTTPS），默认为HTTP */
//        //ossConfig.setProtocol("HTTP");
//        /* 设置用户代理，指HTTP的User-Agent头，默认为aliyun-sdk-java */
//        //ossConfig.setUserAgent("<yourUserAgent>");
//        /* 设置代理服务器验证的用户名，https协议时需要填 */
//        //ossConfig.setProxyUsername("<yourProxyUserName>");
//        /* 设置代理服务器验证的密码，https协议时需要填 */
//        //ossConfig.setProxyPassword("<yourProxyPassword>");
//        //request.setOssConfig(ossConfig);
//
//        UploadVideoImpl uploader = new UploadVideoImpl();
//        UploadVideoResponse response = uploader.uploadVideo(request);
//        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
//        if (response.isSuccess()) {
//            System.out.print("VideoId=" + response.getVideoId() + "\n");
//        } else {
//            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
//            System.out.print("VideoId=" + response.getVideoId() + "\n");
//            System.out.print("ErrorCode=" + response.getCode() + "\n");
//            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
//        }
//        return response;
//
//    }
//
//
//    /**
//     * 上传本地文件
//     * @param ossClient
//     * @param uploadAddress
//     * @param localFile
//     */
//    public static void uploadLocalFile(OSSClient ossClient, JSONObject uploadAddress, String localFile){
//        String bucketName = uploadAddress.getStr("Bucket");
//        String objectName = uploadAddress.getStr("FileName");
////        log.info("bucketName: {}", bucketName);
////        log.info("objectName: {}", objectName);
//        File file = new File(localFile);
//        // 单文件上传
//        ossClient.putObject(bucketName, objectName, file);
//
//        /* 视频点播不支持追加上传
//        // 追加上传
//        ObjectMetadata meta = new ObjectMetadata();
//        meta.setContentType("text/plain");
//        AppendObjectRequest request = new AppendObjectRequest(bucketName, objectName, file, meta);
//        request.setPosition(0L);
//        ossClient.appendObject(request);*/
//    }
//
//    /**
//     * 刷新上传凭证
//     * @param vodClient
//     * @return
//     * @throws ClientException
//     */
//    public static RefreshUploadVideoResponse refreshUploadVideo(DefaultAcsClient vodClient) throws ClientException {
//        RefreshUploadVideoRequest request = new RefreshUploadVideoRequest();
//        request.setAcceptFormat(FormatType.JSON);
//        request.setVideoId("VideoId");
//        //设置请求超时时间
//        request.setSysReadTimeout(1000);
//        request.setSysConnectTimeout(1000);
//        return vodClient.getAcsResponse(request);
//    }
//
//    /**
//     * 获取源文件信息
//     * @return GetMezzanineInfoResponse 获取源文件信息响应数据
//     * @throws Exception
//     */
//    public static GetMezzanineInfoResponse getMezzanineInfo(String videoId) throws Exception {
//        GetMezzanineInfoRequest request = new GetMezzanineInfoRequest();
//        request.setVideoId(videoId);
//        //源片下载地址过期时间
//        request.setAuthTimeout(10L);
//
////        log.info("获取VOD源文件信息请求：{}", JSON.toJSONString(request));
//        GetMezzanineInfoResponse acsResponse = initVodClient().getAcsResponse(request);
////        log.info("获取VOD源文件信息返回：{}", JSON.toJSONString(acsResponse));
//        return acsResponse;
//    }
//
//    /**
//     * 获取播放凭证函数
//     * @param client
//     * @return
//     * @throws Exception
//     */
//    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client, String videoId) throws Exception {
//        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
//        request.setVideoId(videoId);
//        return client.getAcsResponse(request);
//    }
//
//    /**
//     * 获取视频信息
//     * @return GetVideoInfoResponse 获取视频信息响应数据
//     * @throws Exception
//     */
//    public static GetVideoInfoResponse getVideoInfo(String videoId) throws Exception {
//        DefaultAcsClient client = TencentVodUtil.initVodClient();
//        GetVideoInfoRequest request = new GetVideoInfoRequest();
//        request.setVideoId(videoId);
//        GetVideoInfoResponse getVideoInfoResponse = client.getAcsResponse(request);
////        log.info("获取视频信息: {}", JSON.toJSONString(getVideoInfoResponse));
//        return getVideoInfoResponse;
//    }
//
//    /**
//     * 查询视频转码摘要信息
//     */
//    public static GetTranscodeSummaryResponse getTranscodeSummary(String videoId) throws Exception {
//        DefaultAcsClient client = TencentVodUtil.initVodClient();
//        GetTranscodeSummaryRequest request = new GetTranscodeSummaryRequest();
//        request.setVideoIds(videoId);
//        return client.getAcsResponse(request);
//    }
//
//    /**
//     * 获取辅助媒资上传地址和凭证
//     */
//    private static CreateUploadAttachedMediaResponse getUploadAttachedMediaAuthSrt(String fullPath) throws ClientException {
//        CreateUploadAttachedMediaRequest request = new CreateUploadAttachedMediaRequest();
//        var file = new File(fullPath);
//        request.setTitle(file.getName());
//        request.setFileName(fullPath);
//        request.setMediaExt("srt");
//        request.setBusinessType("subtitle");
//        return initVodClient().getAcsResponse(request);
//    }
//
//    /**
//     * 搜索媒资信息
//     * https://help.aliyun.com/document_detail/86044.htm?spm=a2c4g.11186623.0.0.7893715cVg2YeH#doc-api-vod-SearchMedia
//     * @return SearchMediaResponse 搜索媒资信息响应数据
//     * @throws Exception
//     */
//    public static SearchMediaResponse searchByTitle(String title) throws Exception {
//        SearchMediaRequest request = new SearchMediaRequest();
//        request.setFields("Title,CoverURL,Status");
//        // request.setMatch("Status in ('Normal') and Title = '" + title + "'");
//        request.setMatch("Status in ('Normal', 'Transcoding') and Title = '" + title + "'");
//        // request.setMatch("Status = ('Normal')");
//        request.setPageNo(1);
//        request.setPageSize(1);
//        // request.setSearchType("video");
//        request.setSortBy("CreationTime:Desc");
//        return initVodClient().getAcsResponse(request);
//    }
//
//    /**
//     *
//     * @param start '2021-11-01T08:00:00Z'
//     * @param end '2021-11-10T08:00:00Z'
//     * @return
//     * @throws Exception
//     */
//    public static SearchMediaResponse searchByCreationTime(String start, String end) throws Exception {
//        SearchMediaRequest request = new SearchMediaRequest();
//        request.setFields("Title,CoverURL,Status");
//        // request.setMatch("Status in ('Normal') and Title = '" + title + "'");
//        // request.setMatch("Status = ('Normal')");
//        request.setMatch("CreationTime = (" + start + "," + end + ")");
//        request.setPageNo(1);
//        request.setPageSize(10);
//        // request.setSearchType("video");
//        request.setSortBy("CreationTime:desc");
//        return initVodClient().getAcsResponse(request);
//    }
//
//    /**
//     * 删除视频
//     * @return DeleteVideoResponse 删除视频响应数据
//     * @throws Exception
//     */
//    public static DeleteVideoResponse deleteVideo(String videoIds) throws Exception {
//        DeleteVideoRequest request = new DeleteVideoRequest();
//        //支持传入多个视频ID，多个用逗号分隔
//        request.setVideoIds(videoIds);
//        DeleteVideoResponse acsResponse = initVodClient().getAcsResponse(request);
////        log.info("删除视频结果: {}", JSON.toJSONString(acsResponse));
//        return acsResponse;
//    }
//
//    public static String uploadSubtitle(String subtitleFullPath) {
//        try {
//            CreateUploadAttachedMediaResponse response;
//
//            response = getUploadAttachedMediaAuthSrt(subtitleFullPath);
////            log.info("获取辅助媒资地址和凭证：{}", JSON.toJSONString(response));
//
//            JSONObject uploadAuth = decodeBase64(response.getUploadAuth());
//            JSONObject uploadAddress = decodeBase64(response.getUploadAddress());
//
//            OSSClient ossClient = initOssClient(uploadAuth, uploadAddress);
//
//            uploadLocalFile(ossClient, uploadAddress, subtitleFullPath);
////            log.info("上传辅助媒资成功: {}", response.getFileURL());
//            return response.getFileURL();
//        } catch (Exception e) {
//            throw new BusinessException(BusinessExceptionEnum.OBJ_SERIALIZE_2_JSON_FAILED);
////            throw new BusinessException(BusinessExceptionEnum.GEN_SUBTITLE_ERROR);
//        }
//    }
//
//    /**
//     * base64解码
//     * @param uploadAuth
//     * @return
//     */
//    private static JSONObject decodeBase64(String uploadAuth) {
//        return JSONUtil.toBean(new String(Base64.getDecoder().decode(uploadAuth)), JSONObject.class);
//    }
//
//    /**
//     * 计算音频转字幕应收金额
//     */
//    public static BigDecimal calAmount(String videoId) {
//        try {
//            GetVideoInfoResponse videoInfo = getVideoInfo(videoId);
//            Float duration = videoInfo.getVideo().getDuration();
////            log.info("视频：{}，时长：{}，单价：{}", videoId, duration, filetransAudioPrice);
//            BigDecimal amount = new BigDecimal(duration).multiply(fileTransAudioPrice).divide(new BigDecimal("60"), 2, BigDecimal.ROUND_HALF_UP);
//            // 最低收1分钱
//            if (amount.compareTo(BigDecimal.ZERO) == 0) {
//                amount = new BigDecimal("0.01");
//            }
//            return amount;
//        } catch (Exception e) {
////            log.error("计算音频转字幕应收金额异常", e);
////            throw new BusinessException(BusinessExceptionEnum.FILETRANS_CAL_AMOUNT_ERROR);
//            throw new BusinessException(BusinessExceptionEnum.OBJ_SERIALIZE_2_JSON_FAILED);
//        }
//    }

}

