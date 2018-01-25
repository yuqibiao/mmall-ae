/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/1/24 16:35:36                           */
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
   checked              smallint default 1 comment '1-��ѡ 2- ����ѡ Ĭ��1',
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (cart_Id)
);

/*==============================================================*/
/* Table: mall_logistics_info                                   */
/*==============================================================*/
create table mall_logistics_info
(
   logistics_id         bigint not null auto_increment,
   user_id              bigint comment '�û�Id������',
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
);

alter table mall_logistics_info comment '������Ϣ';

/*==============================================================*/
/* Table: mall_order                                            */
/*==============================================================*/
create table mall_order
(
   order_Id             bigint not null auto_increment,
   user_id              bigint comment '�û�Id',
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
);

alter table mall_order comment '��Ʒ����';

/*==============================================================*/
/* Table: mall_order_item                                       */
/*==============================================================*/
create table mall_order_item
(
   order_item_id        bigint not null auto_increment,
   order_Id             bigint,
   product_id           bigint,
   quantity             int,
   current_unit_price   decimal(20,2) comment '���ɶ���ʱ��Ʒ�ļ۸�',
   product_name         varchar(64),
   product_image        varchar(128),
   total_price          decimal(20,2),
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (order_item_id)
);

alter table mall_order_item comment '������ϸ��Ϣ';

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
);

alter table mall_pay_info comment '֧����ص���Ϣ';

/*==============================================================*/
/* Table: mall_permission                                       */
/*==============================================================*/
create table mall_permission
(
   permission_id        int not null auto_increment,
   parent_id            int,
   name                 varchar(64) not null,
   description          varchar(256),
   status               smallint default 1 comment '1-���� 2-������ Ĭ��1',
   code                 smallint,
   target               varchar(128) comment 'action��url ',
   type                 smallint default 1 comment '1-Ȩ�� 2-�˵� Ĭ��1',
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (permission_id)
);

alter table mall_permission comment '����Ȩ��';

/*==============================================================*/
/* Table: mall_product                                          */
/*==============================================================*/
create table mall_product
(
   product_id           bigint not null auto_increment,
   category_id          bigint comment '��Ʒ����Id ����������Id',
   name                 varchar(64) not null,
   subtitle             varchar(256),
   main_image           varchar(128),
   sub_image            text comment '��json��ʽ��ͼƬ��ַ',
   detail               text,
   price                decimal(20,2) not null,
   stock                bigint,
   status               smallint not null default 1 comment '1-���� 2-�¼� 3-ɾ�� Ĭ��Ϊ1',
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (product_id)
);

alter table mall_product comment '��Ʒ��Ϣ��';

/*==============================================================*/
/* Table: mall_product_category                                 */
/*==============================================================*/
create table mall_product_category
(
   category_id          bigint not null auto_increment comment '��Ʒ����Id ����������Id',
   parent_id            bigint comment '��һ�������Id�����η��ࣩ',
   name                 varchar(32) not null,
   status               smallint default 1 comment '1-���� 2-�ϳ� Ĭ��Ϊ1',
   code                 smallint,
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (category_id)
);

alter table mall_product_category comment '��Ʒ�ķ�����Ϣ';

/*==============================================================*/
/* Table: mall_role                                             */
/*==============================================================*/
create table mall_role
(
   role_id              int not null auto_increment,
   name                 varchar(64) not null,
   code                 smallint,
   description          varchar(256),
   status               smallint default 1 comment '1-���� 2-��ֹ Ĭ��Ϊ1',
   create_time          datetime default CURRENT_TIMESTAMP,
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   primary key (role_id)
);

alter table mall_role comment '��ͬ��ɫ�в�ͬ��Ȩ��';

/*==============================================================*/
/* Table: mall_role_permission                                  */
/*==============================================================*/
create table mall_role_permission
(
   permission_id        int not null,
   role_id              int not null,
   primary key (permission_id, role_id)
);

alter table mall_role_permission comment '��ɫȨ�޹�ϵ��Ӧ��';

/*==============================================================*/
/* Table: mall_user                                             */
/*==============================================================*/
create table mall_user
(
   user_id              bigint not null auto_increment comment '�û�Id',
   username             varchar(64) not null comment 'Ψһ',
   password             varchar(64) not null comment 'MD5����',
   phone                varchar(16),
   email                varchar(64),
   status               smallint default 1 comment '1-���� 2-������ Ĭ��Ϊ1',
   question             varchar(128),
   answer               varchar(128),
   create_time          datetime default CURRENT_TIMESTAMP comment '���ݲ���ʱ��',
   update_ime           datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '���ݸ���ʱ��',
   primary key (user_id),
   unique key AK_Key_unique (username)
);

alter table mall_user comment '�û���';

/*==============================================================*/
/* Table: mall_user_role                                        */
/*==============================================================*/
create table mall_user_role
(
   role_id              int not null,
   user_id              bigint not null comment '�û�Id',
   primary key (role_id, user_id)
);

alter table mall_user_role comment '�û���ɫ��Ӧ��ϵ��';

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

