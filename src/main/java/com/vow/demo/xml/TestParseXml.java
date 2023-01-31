package com.vow.demo.xml;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: Administrator
 * @Date: 2022/11/15 13:51
 */
public class TestParseXml {

    private static final AtomicLong elementInx = new AtomicLong(0);
    private static final AtomicLong lineNumber = new AtomicLong(0);

    /*public static void main(String[] args) throws DocumentException, IOException {

        List<MyDocument> list = new ArrayList<>();

        SAXReader reader = new SAXReader();
        reader.setDefaultHandler(new ElementHandler() {
            @Override
            public void onStart(ElementPath elementPath) {
                elementInx.incrementAndGet();
                lineNumber.incrementAndGet();
                String path = elementPath.getPath();
                Element current = elementPath.getCurrent();
                String name = current.getName();
                int size = elementPath.size();
                List<Attribute> attributes = current.attributes();
                Map<String, String> map = new HashMap<>();
                if (!CollectionUtils.isEmpty(attributes)) {
                    attributes.forEach(e -> {
                        map.put(e.getName(), e.getValue());
                    });
                }

                MyDocument myDocument = new MyDocument();
                myDocument.setPath(path);
                myDocument.setName(name);
                myDocument.setStartPosition(elementInx.intValue());
                myDocument.setAttributes(map);
                list.add(myDocument);

                System.out.println(myDocument.toString());
            }

            @Override
            public void onEnd(ElementPath elementPath) {
                String path = elementPath.getPath();
                Element current = elementPath.getCurrent();
                int size = elementPath.size();

                MyDocument myDocument = new MyDocument();
                for (int i = list.size() - 1; i > 0; i--) {
                    myDocument = list.get(i);
                    if (myDocument.getPath().equals(path)) {
                        break;
                    }
                }
                myDocument.setEndPosition(lineNumber.intValue());
                System.out.println(myDocument.toString());
            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println("开始获取文件行数----time:" + simpleDateFormat.format(System.currentTimeMillis()));
        Path path = Paths.get("D:\\Documents\\金华职业技术学院学士苑9#学生公寓改建项目-换模板-控制价.xml");
        long count = Files.lines(path).count();
        System.out.println("文件行数-----count:" + count);
        System.out.println("获取到文件行数----time:" + simpleDateFormat.format(System.currentTimeMillis()));

        System.out.println("开始解析文件----time:" + simpleDateFormat.format(System.currentTimeMillis()));
        File file = new File("D:\\Documents\\金华职业技术学院学士苑9#学生公寓改建项目-换模板-控制价.xml");
        //FileInputStream fileInputStream = new FileInputStream(file);
        Document document = reader.read(file);
        Element root = document.getRootElement();
        Element projectInfo = root.element("建设项目信息表");
        Attribute price = projectInfo.attribute("造价金额");
        System.out.println(price);
        System.out.println();
        System.out.println("解析文件完成----time:" + simpleDateFormat.format(System.currentTimeMillis()));
    }*/
}
