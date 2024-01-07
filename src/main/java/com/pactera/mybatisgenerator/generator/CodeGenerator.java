package com.pactera.mybatisgenerator.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;

/**
 * @author zwk
 * Date 2023/2/16 17:56
 * Description: code-generator
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir"); //获取项目路径
        String filePath = projectPath + "/src/main/java";  //java下的文件路径
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/seckill?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai","root","root")
                //2、全局配置
                .globalConfig(builder -> {
                    builder.author("zwk") // 设置作者名
                            .outputDir(filePath)//生成的输出路径
//                            .enableSwagger()//开启swagger，需要添加swagger依赖并配置
                            .dateType(DateType.ONLY_DATE)//时间策略
                            .commentDate("yyyy年MM月dd日")//格式化时间格式
                            .disableOpenDir();//禁止打开输出目录，默认false
                })
                //3、包配置
                .packageConfig(builder -> {
                    builder.parent("com.pactera")//父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
                            .moduleName("redisDome")   //设置模块包名
                            .entity("gen")   //pojo 实体类包名
                            .service("service") //Service 包名
                            .serviceImpl("service.impl") // ***ServiceImpl 包名
                            .mapper("mapper")   //Mapper 包名
                            .xml("mapper.xml")  //Mapper XML 包名
                            .controller("controller") //Controller 包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"/src/main/resources/mapper"));    //配置 mapper.xml 路径信息：项目的 resources 目录下
                })
                //4、策略配置
                .strategyConfig(builder -> {
                    builder
                            .enableCapitalMode()    //开启大写命名
                            .enableSkipView()   //创建实体类的时候跳过视图
                            .addInclude("t_user") // 设置需要生成的数据表名
//                            .addTableSuffix("") //设置 过滤 表的后缀
                            .addTablePrefix("t_") // 设置 过滤 表的前缀
                            //4.1、实体类策略配置
                            .entityBuilder()
                            .enableChainModel() //开启链式模型
                            .enableFileOverride()
                            //.disableSerialVersionUID()  //默认是开启实体类序列化，可以手动disable使它不序列化。由于项目中需要使用序列化就按照默认开启了
                            .enableTableFieldAnnotation()       // 开启生成实体时生成字段注解
                            .enableLombok() //开启 Lombok
                            .versionColumnName("version")   //乐观锁字段名(数据库)
                            .versionPropertyName("version") //乐观锁属性名(实体)
                            .logicDeleteColumnName("deleted")   //逻辑删除字段名(数据库)
                            .logicDeletePropertyName("deleteFlag")  //逻辑删除属性名(实体)
                            .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：默认是下划线转驼峰命。这里可以不设置
                            .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命。（默认是和naming一致，所以也可以不设置）
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("modify_time", FieldFill.INSERT_UPDATE)
                            )   //添加表字段填充，"create_time"字段自动填充为插入时间，"modify_time"字段自动填充为插入修改时间
                            .idType(IdType.AUTO)    //设置主键自增

                            //4.2、Controller策略配置
                            .controllerBuilder()
                            .enableHyphenStyle()    //开启驼峰连转字符
                            .formatFileName("%sController") //格式化 Controller 类文件名称，%s进行匹配表名，如 UserController
                            .enableRestStyle()  //开启生成 @RestController 控制器
                            .enableFileOverride()

                            //4.3、service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                            .enableFileOverride()
                            //4.4、Mapper策略配置
                            .mapperBuilder()
                            .mapperAnnotation(Mapper.class)//开启 @Mapper 注解
                            .superClass(BaseMapper.class)   //设置父类
                            .enableBaseResultMap()  //启用 BaseResultMap 生成
                            .enableBaseColumnList() //启用 BaseColumnList
                            .formatMapperFileName("%sMapper")   //格式化 mapper 文件名称
                            .enableFileOverride()
                            .formatXmlFileName("%sMapper") //格式化Xml文件名称
                            .formatMapperFileName("%sMapper");   //格式化Mapper文件名称
                })
                .templateConfig(builder -> {
                    builder.entity("templates/entity.java")
                            .controller("templates/controller.java")
                            .service("templates/service.java")
                            .serviceImpl("templates/serviceImpl.java")
                            .mapper("templates/mapper.java")
                            .xml("templates/mapper.xml")
                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine())	//本人选择了Freemarker
                //6、执行
                .execute();
    }
}