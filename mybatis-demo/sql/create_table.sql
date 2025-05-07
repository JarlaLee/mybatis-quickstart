create database mybatisdemo;
use mybatisdemo;

-- 删除 tb_user 表
drop table if exists tb_user;

-- 创建 tb_user 表
create table tb_user (
                         id int primary key auto_increment,
                         username varchar(20),
                         password varchar(20),
                         gender char(1),
                         addr varchar(30)
);

INSERT INTO tb_user VALUES (1, 'zhangsan', '123', '男', '北京');
INSERT INTO tb_user VALUES (2, '李四', '234', '女', '天津');
INSERT INTO tb_user VALUES (3, '王五', '11', '男', '西安');


-- 删除 tb_brand 表
drop table if exists tb_brand;

-- 创建 tb_brand 表
create table tb_brand (
    -- id 主键
                          id int primary key auto_increment,
    -- 品牌名称
                          brand_name varchar(20),
    -- 企业名称
                          company_name varchar(20),
    -- 排序字段
                          ordered int,
    -- 描述信息
                          description varchar(100),
    -- 状态：0：禁用  1：启用
                          status int
);

-- 添加数据
insert into tb_brand (brand_name, company_name, ordered, description, status)
values
    ('三只松鼠', '三只松鼠股份有限公司', 5, '好吃不上火', 0),
    ('华为', '华为技术有限公司', 100, '华为致力于把数字世界带入每个人、每个家庭、每个组织，构建万物互联的智能世界', 1),
    ('小米', '小米科技有限公司', 50, 'are you ok', 1);

SELECT * FROM tb_brand;