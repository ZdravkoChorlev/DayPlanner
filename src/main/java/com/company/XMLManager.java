package com.company;

import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class XMLManager  {

    public void saveXml(Map<String, Task> hashTask) {

        try {
            Document document = new Document();
            document.setRootElement(new Element("Tasks"));

            for(Task map : hashTask.values()) {
                Element task = new Element("Task");
                task.setAttribute("key", map.getDay() + "." + map.getHour());
                task.addContent(new Element("description").setText(map.getDescription()));
                task.addContent(new Element("day").setText("" + map.getDay()));
                task.addContent(new Element("hour").setText("" +map.getHour()));
                document.getRootElement().addContent(task);
            }

            XMLOutputter print = new XMLOutputter(Format.getPrettyFormat());
         //print.output(document, System.out);
            print.output(document, new FileOutputStream("xmlSaves.xml"));

        } catch (IOException e) {
            System.out.println("Loading XML error!");
        }
    }

    public void unloadXml(Map<String, Task> hashTask) {
        try {
            try {
                File file = new File("xmlSaves.xml");
                SAXBuilder builder = new SAXBuilder();
                Document document = builder.build(file);
                Element element = document.getRootElement();

            List<Element> listOfTasks = element.getChildren();

            for (int i = 0; i < listOfTasks.size(); i++) {

                Element tasks = listOfTasks.get(i);

                Attribute attribute = tasks.getAttribute("key");
                String description = tasks.getChild("description").getText();
                int day = Integer.parseInt(tasks.getChild("day").getText());
                int hour = Integer.parseInt(tasks.getChild("hour").getText());
                Task t = new Task(description, day, hour);
                hashTask.put(attribute.getValue(), t);
            }
            } catch (IOException e) {
                System.out.println("File excepctio");
            }
        } catch (JDOMException e) {
            System.out.println("Unloading XML error!");
        }
    }
}
