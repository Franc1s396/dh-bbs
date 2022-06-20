package org.francis.dh.admin;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Franc1s
 * @date 2022/6/17
 * @apiNote
 */
@SpringBootTest
public class CodeGeneration {

    @Test
    void generate(){
        //构建一个 代码生成器 对象
        AutoGenerator autoGenerator = new AutoGenerator();
        //配置策略
        //1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String property = System.getProperty("user.dir");//获得程序当前路径
        globalConfig.setOutputDir(property + "/src/main/java");
        globalConfig.setAuthor("francis");
        globalConfig.setOpen(false);//是否自动打开资源管理器文件夹（此电脑那个）
        globalConfig.setFileOverride(false);//是否覆盖
        globalConfig.setServiceName("%sService");//去除Service的I前缀
        globalConfig.setDateType(DateType.TIME_PACK);
        globalConfig.setSwagger2(true);
        autoGenerator.setGlobalConfig(globalConfig);
        //2.设置数据源
        DataSourceConfig DSC = new DataSourceConfig();
        DSC.setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/dh_bbs?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai")
                .setUsername("root")
                .setPassword("ma321388")
                .setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(DSC);
        //3.包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("")//最上级的文件夹名字 不理解就看项目结构
                .setParent("org.francis.dh.admin")//在什么文件夹下生成
                .setEntity("entity")//实体类文件夹名字
                .setMapper("mapper")//Mapper文件夹名字
                .setController("controller")
                .setService("service");
        autoGenerator.setPackageInfo(pc);

        //4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("b_post_like","b_user_like_post")//设置要映射的表名
                .setNaming(NamingStrategy.underline_to_camel)//从数据库表到文件的命名策略
                .setColumnNaming(NamingStrategy.underline_to_camel)//underline_to_camel:下划线转驼峰命名
                .setTablePrefix("b_") //生成实体时去掉表前缀
                .setEntityLombokModel(true)//实体类lombok是否开启
                .setRestControllerStyle(true)//REST风格
                .setLogicDeleteFieldName("deleted");//逻辑删除字段
        //自动填充装置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);
        //乐观锁
        //strategy.setVersionFieldName("version");
        strategy.setControllerMappingHyphenStyle(true);
        autoGenerator.setStrategy(strategy);
        autoGenerator.execute();
    }
}
