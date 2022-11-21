package com.vow.demo.xml;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.springframework.util.CollectionUtils;

import java.io.File;
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

    public static void main(String[] args) throws DocumentException {

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

        reader.read(new File("D:\\Documents\\智云链\\xml文件\\测试20211020001招中标和计量结算.xml"));
    }
}
