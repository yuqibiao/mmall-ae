/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/1/26 17:07:42                           */
/*==============================================================*/


drop table if exists mall_cart;

drop table if exists mall_logistics_info;

drop table if exists mall_order;

drop table if exists mall_order_item;

drop table if exists mall_pay_info;

drop table if exists mall_permission;

drop table if exists mall_product;

drop table if exists mall_product_category;

drop table if exists mall_role;

drop table if exists mall_role_permission;

drop table if exists mall_user;

drop table if exists mall_user_role;

/*==============================================================*/
/* Table: mall_cart                                             */
/*==============================================================*/
create table mall_cart
(
   cart_Id              bigint not null auto_increment,
   user_id              bigint,
   product_id           bigint,
   quantity             int,
   checked              smallint default 1 comment '1-勾选 2- 不勾选 默认1',
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (cart_Id)
)
DEFAULT CHARACTER SET = utf8;

/*==============================================================*/
/* Table: mall_logistics_info                                   */
/*==============================================================*/
create table mall_logistics_info
(
   logistics_id         bigint not null auto_increment,
   user_id              bigint comment '用户Id、自增',
   receiver_name        varchar(32) not null,
   receiver_tel         varchar(16),
   receiver_phone       varchar(16) not null,
   receiver_province    varchar(16) not null,
   receiver_city        varchar(16) not null,
   receiver_district    varchar(16) not null,
   receiver_address     varchar(64) not null,
   receiver_zip_code    varchar(8),
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (logistics_id)
)
DEFAULT CHARACTER SET = utf8;

alter table mall_logistics_info comment '物流信息';

/*==============================================================*/
/* Table: mall_order                                            */
/*==============================================================*/
create table mall_order
(
   order_Id             bigint not null auto_increment,
   user_id              bigint comment '用户Id',
   logistics_id         bigint,
   order_no             bigint(20) not null,
   payment              decimal(20,2) not null,
   payment_type         smallint,
   postage              decimal,
   status               smallint,
   payment_time         datetime,
   send_time            datetime,
   end_time             datetime,
   close_time           datetime,
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (order_Id)
)
DEFAULT CHARACTER SET = utf8;

alter table mall_order comment '商品订单';

/*==============================================================*/
/* Table: mall_order_item                                       */
/*==============================================================*/
create table mall_order_item
(
   order_item_id        bigint not null auto_increment,
   order_Id             bigint,
   product_id           bigint,
   quantity             int,
   current_unit_price   decimal(20,2) comment '生成订单时商品的价格',
   product_name         varchar(64),
   product_image        varchar(128),
   total_price          decimal(20,2),
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (order_item_id)
)
DEFAULT CHARACTER SET = utf8;

alter table mall_order_item comment '订单详细信息';

/*==============================================================*/
/* Table: mall_pay_info                                         */
/*==============================================================*/
create table mall_pay_info
(
   pay_id               bigint not null auto_increment,
   order_Id             bigint,
   pay_platform         smallint,
   platform_no          varchar(128),
   platform_status      varchar(32),
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (pay_id)
)
DEFAULT CHARACTER SET = utf8;

alter table mall_pay_info comment '支付相关的信息';

/*==============================================================*/
/* Table: mall_permission                                       */
/*==============================================================*/
create table mall_permission
(
   permission_id        int not null auto_increment,
   parent_id            int,
   name                 varchar(64) not null,
   description          varchar(256),
   status               smallint default 1 comment '1-可用 2-不可用 默认1',
   code                 smallint,
   target               varchar(128) comment 'action、url ',
   type                 smallint default 1 comment '1-权限 2-菜单 默认1',
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (permission_id)
)
DEFAULT CHARACTER SET = utf8;

alter table mall_permission comment '操作权限';

/*==============================================================*/
/* Table: mall_product                                          */
/*==============================================================*/
create table mall_product
(
   product_id           bigint not null auto_increment,
   category_id          bigint comment '商品分类Id 、自增类型Id',
   name                 varchar(64) not null,
   subtitle             varchar(256),
   main_image           varchar(128),
   sub_image            text comment '存json格式的图片地址',
   detail               text,
   price                decimal(20,2) not null,
   stock                bigint,
   status               smallint not null default 1 comment '1-在售 2-下架 3-删除 默认为1',
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (product_id)
)
DEFAULT CHARACTER SET = utf8;

alter table mall_product comment '商品信息表';

/*==============================================================*/
/* Table: mall_product_category                                 */
/*==============================================================*/
create table mall_product_category
(
   category_id          bigint not null auto_increment comment '商品分类Id 、自增类型Id',
   parent_id            bigint comment '上一级分类的Id（树形分类）',
   name                 varchar(32) not null,
   status               smallint default 1 comment '1-正常 2-废除 默认为1',
   code                 smallint,
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (category_id)
)
DEFAULT CHARACTER SET = utf8;

alter table mall_product_category comment '商品的分类信息';

/*==============================================================*/
/* Table: mall_role                                             */
/*==============================================================*/
create table mall_role
(
   role_id              int not null auto_increment,
   name                 varchar(64) not null,
   code                 smallint,
   description          varchar(256),
   status               smallint default 1 comment '1-可用 2-禁止 默认为1',
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (role_id)
)
DEFAULT CHARACTER SET = utf8;

alter table mall_role comment '不同角色有不同的权限';

/*==============================================================*/
/* Table: mall_role_permission                                  */
/*==============================================================*/
create table mall_role_permission
(
   permission_id        int not null,
   role_id              int not null,
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (permission_id, role_id)
)
DEFAULT CHARACTER SET = utf8;

alter table mall_role_permission comment '角色权限关系对应表';

/*==============================================================*/
/* Table: mall_user                                             */
/*==============================================================*/
create table mall_user
(
   user_id              bigint not null auto_increment comment '用户Id',
   username             varchar(64) not null comment '唯一',
   password             varchar(64) not null comment 'MD5加密',
   phone                varchar(16),
   email                varchar(64),
   status               smallint default 1 comment '1-可能 2-不可用 默认为1',
   question             varchar(128),
   answer               varchar(128),
   create_time          datetime default CURRENT_TIMESTAMP comment '数据插入时间',
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '数据更新时间',
   primary key (user_id),
   unique key AK_Key_unique (username)
)
DEFAULT CHARACTER SET = utf8;

alter table mall_user comment '用户表';

/*==============================================================*/
/* Table: mall_user_role                                        */
/*==============================================================*/
create table mall_user_role
(
   role_id              int not null,
   user_id              bigint not null comment '用户Id',
   create_time          datetime default CURRENT_TIMESTAMP,
   updatetime           datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (role_id, user_id)
)
DEFAULT CHARACTER SET = utf8;

alter table mall_user_role comment '用户角色对应关系表';

alter table mall_cart add constraint FK_Reference_3 foreign key (user_id)
      references mall_user (user_id) on delete restrict on update restrict;

alter table mall_cart add constraint FK_Reference_4 foreign key (product_id)
      references mall_product (product_id) on delete restrict on update restrict;

alter table mall_logistics_info add constraint FK_Reference_6 foreign key (user_id)
      references mall_user (user_id) on delete restrict on update restrict;

alter table mall_order add constraint FK_Reference_5 foreign key (user_id)
      references mall_user (user_id) on delete restrict on update restrict;

alter table mall_order add constraint FK_Reference_7 foreign key (logistics_id)
      references mall_logistics_info (logistics_id) on delete restrict on update restrict;

alter table mall_order_item add constraint FK_Reference_8 foreign key (order_Id)
      references mall_order (order_Id) on delete restrict on update restrict;

alter table mall_order_item add constraint FK_Reference_9 foreign key (product_id)
      references mall_product (product_id) on delete restrict on update restrict;

alter table mall_pay_info add constraint FK_Reference_10 foreign key (order_Id)
      references mall_order (order_Id) on delete restrict on update restrict;

alter table mall_permission add constraint FK_Reference_11 foreign key (parent_id)
      references mall_permission (permission_id) on delete restrict on update restrict;

alter table mall_product add constraint FK_Reference_2 foreign key (category_id)
      references mall_product_category (category_id) on delete restrict on update restrict;

alter table mall_product_category add constraint FK_Reference_1 foreign key (parent_id)
      references mall_product_category (category_id) on delete restrict on update restrict;

alter table mall_role_permission add constraint FK_Reference_14 foreign key (permission_id)
      references mall_permission (permission_id) on delete restrict on update restrict;

alter table mall_role_permission add constraint FK_Reference_15 foreign key (role_id)
      references mall_role (role_id) on delete restrict on update restrict;

alter table mall_user_role add constraint FK_Reference_12 foreign key (role_id)
      references mall_role (role_id) on delete restrict on update restrict;

alter table mall_user_role add constraint FK_Reference_13 foreign key (user_id)
      references mall_user (user_id) on delete restrict on update restrict;

