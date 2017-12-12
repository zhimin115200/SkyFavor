CREATE TABLE `sf_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `email` varchar(64) NOT NULL DEFAULT '' COMMENT '邮箱账号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sf_user用户';

CREATE TABLE `sf_mail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `email_id` varchar(32) NOT NULL DEFAULT '' COMMENT '发送id',
  `email` varchar(64) NOT NULL DEFAULT '' COMMENT '邮箱账号',
  `content` TEXT NOT NULL COMMENT '邮件内容',
  `type` int(2) NOT NULL COMMENT '内容类型：1.验证码，2普通内容',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sf_mail邮件内容';

CREATE TABLE `sf_folder` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `folder_id` varchar(32) NOT NULL DEFAULT '' COMMENT '文件夹id',
  `folder_name` varchar(64) NOT NULL DEFAULT '' COMMENT '文件夹名',
  `email` varchar(64) NOT NULL COMMENT '邮箱账号',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sf_folder文件夹';

CREATE TABLE `sf_file` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `file_id` varchar(32) NOT NULL DEFAULT '' COMMENT '文件id',
  `folder_id` varchar(64) NOT NULL COMMENT '文件夹id',
	`content` TEXT NOT NULL COMMENT '文件内容',
	`type` int(2) NOT NULL COMMENT '文件类型：1文字，2链接，3',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sf_file文件';

