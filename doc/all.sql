-- 短信验证码
drop table if exists `mast_sms_code`;
create table `mast_sms_code` (
    `msc_id` bigint not null comment 'id',
    `msc_account` varchar(50) not null comment '账号',
    `msc_account_type` char(1) not null comment '账号类型|枚举[AccountTypeEnum]：MOBILE("1", "手机"), EMAIL("2", "邮箱")',
    `msc_code` varchar(6) not null comment '验证码',
    `msc_usage` varchar(20) not null comment '用途|枚举[SmsCodeUsageEnum]：REGISTER("0", "注册"), FORGET_PASSWORD("1", "忘记密码")',
    `msc_status` char(1) not null comment '状态|枚举[SmsCodeStatusEnum]：USED("1", "已使用"), NOT_USED("0", "未使用")',
    `msc_created_at` datetime(3) comment '创建时间',
    `msc_updated_at` datetime(3) comment '修改时间',
    primary key (`msc_id`)
) engine=innodb default charset=utf8 comment='短信验证码表';

drop table if exists `mast_member`;
create table `mast_member` (
                               `mm_id` bigint not null comment 'id',
                               `mm_account` varchar(50) not null comment '手机号',
                               `mm_password` char(32) not null comment '密码',
                               `mm_nickname` varchar(50) comment '昵称',
                               `mm_created_at` datetime(3) comment '创建时间',
                               `mm_updated_at` datetime(3) comment '修改时间',
                               primary key (`mm_id`),
                               unique key `account_unique` (`mm_account`)
) engine=innodb default charset=utf8 comment='会员表';

drop table if exists `mast_file_trans`;
create table `mast_file_trans` (
                                  `id` bigint not null comment 'id',
                                  `member_id` bigint not null comment '会员ID',
                                  `name` varchar(200) not null comment '名称',
                                  `second` int comment '音频文件时长|秒',
                                  `amount` decimal(6,2) comment '金额|元，second*单价',
                                  `audio` varchar(500) comment '文件链接',
                                  `file_sign` char(32) comment '文件签名md5',
                                  `pay_status` char(2) comment '支付状态|枚举[FiletransPayStatusEnum];',
                                  `status` char(2) comment '识别状态|枚举[FiletransStatusEnum];',
                                  `lang` char(16) not null comment '音频语言|枚举[FiletransLangEnum]',
                                  `vod` char(32) comment 'VOD|videoId',
                                  `task_id` char(32) comment '任务ID',
                                  `trans_status_code` int comment '转换状态码',
                                  `trans_status_text` varchar(200) comment '转换状态说明',
                                  `trans_time` datetime(3) comment '转换时间|开始转换的时间',
                                  `solve_time` datetime(3) comment '完成时间|录音文件识别完成的时间',
                                  `created_at` datetime(3) comment '创建时间',
                                  `updated_at` datetime(3) comment '修改时间',
                                  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='语音识别表';
