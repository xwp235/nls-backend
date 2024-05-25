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
