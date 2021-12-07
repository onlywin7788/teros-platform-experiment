package com.teros.dashboard.service.dsm.flow;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.teros.ext.common.file.CommonFile;
import com.teros.ext.common.parser.JsonParser;
import com.teros.ext.common.parser.XmlParser;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import java.util.*;

class LayoutInfo {
    public String id = "";
    public String classNumber = "";
    public String type = "";

    public String input = "";
    public String output = "";
}

@Service
public class FlowService {

    public String getResult(String requestLayout) throws Exception {

        String result = "";

        ArrayList<LayoutInfo> layoutList;
        layoutList = getLayoutInfo(requestLayout);
        result = getConfigContents(layoutList);

        return result;
    }

    private String getConfigContents(ArrayList<LayoutInfo> layoutInfoArrayList) {
        String result = "";
        Element rootElement = null;
        Element propElement = null;
        Element flowElement = null;
        Element paramElement = null;
        Element definitionElement = null;
        Element settingElement = null;

        try {
            XmlParser xmlParser = new XmlParser();
            xmlParser.createXmlDocument();

            rootElement = xmlParser.createElement(null, "config", "");

            // 1 depth
            propElement = xmlParser.createElement(rootElement, "properties", "");

            paramElement = xmlParser.createElement(propElement, "param", "");
            xmlParser.addElementProperty(paramElement, "key", "flow.name");
            xmlParser.addElementProperty(paramElement, "value", "");

            paramElement = xmlParser.createElement(propElement, "param", "");
            xmlParser.addElementProperty(paramElement, "key", "flow.service.mode");
            xmlParser.addElementProperty(paramElement, "value", "online");

            paramElement = xmlParser.createElement(propElement, "param", "");
            xmlParser.addElementProperty(paramElement, "key", "flow.service.port");
            xmlParser.addElementProperty(paramElement, "value", "38088");

            // 2 depth
            flowElement = xmlParser.createElement(rootElement, "flow", "");
            definitionElement = xmlParser.createElement(flowElement, "definition", "");

            for (int i = 0; i < layoutInfoArrayList.size(); i++) {

                LayoutInfo layoutInfo = layoutInfoArrayList.get(i);
                Element nodeElement = xmlParser.createElement(definitionElement, "node", "");
                xmlParser.addElementProperty(nodeElement, "id", layoutInfo.id);
                xmlParser.addElementProperty(nodeElement, "number", layoutInfo.classNumber);
                xmlParser.addElementProperty(nodeElement, "class", layoutInfo.type);
                xmlParser.addElementProperty(nodeElement, "input", layoutInfo.input);
                xmlParser.addElementProperty(nodeElement, "output", layoutInfo.output);
            }

            settingElement = xmlParser.createElement(flowElement, "setting", "");
            for (int i = 0; i < layoutInfoArrayList.size(); i++) {
                LayoutInfo layoutInfo = layoutInfoArrayList.get(i);

                Element nodeElement = xmlParser.createElement(settingElement, "node", "");
                xmlParser.addElementProperty(nodeElement, "id", layoutInfo.id);

                Element paramsElement = xmlParser.createElement(nodeElement, "params", "");
                paramElement = xmlParser.createElement(paramsElement, "param", "");
                xmlParser.addElementProperty(paramElement, "key", "component.path");
                xmlParser.addElementProperty(paramElement, "value", "com.ext.teros.component.Executor");

            }

            result = xmlParser.getDocumentString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private ArrayList<LayoutInfo> getLayoutInfo(String contents) throws Exception {

        String INPUT_PREFIX = "inputs/input";
        String OUTPUT_PREFIX = "outputs/output";

        int INPUT_PREFIX_TYPE = 0;
        int OUTPUT_PREFIX_TYPE = 1;

        ArrayList<LayoutInfo> layoutArray = new ArrayList<LayoutInfo>();

        JsonParser jsonParser = new JsonParser();
        jsonParser.loadString(contents);

        int mainIdx = 1;
        int subIdx = 1;

        JsonElement dataElementTest = jsonParser.getJsonElementFromPath("drawflow/Home/data");
        JsonObject result = dataElementTest.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = result.entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet) {

            String nodeEntryNumber = entry.getKey();

            JsonElement dataElement = jsonParser.getJsonElementFromPath("drawflow/Home/data/" + nodeEntryNumber);
            if (dataElement == null)
                break;

            LayoutInfo layoutInfo = new LayoutInfo();

            String nodeType = jsonParser.getJsonElementFromPath(dataElement, "name").getAsString();
            layoutInfo.classNumber = nodeEntryNumber + "";
            layoutInfo.type = nodeType;
            layoutInfo.id = nodeType + "-"
                    + String.format("%02d", Integer.parseInt(layoutInfo.classNumber));

            // input element

            for (int i = 0; i < 2; i++) {

                int prefixType = INPUT_PREFIX_TYPE;
                String prefixResult = "";

                String prefix = INPUT_PREFIX;
                if (i > 0) {
                    prefixType = OUTPUT_PREFIX_TYPE;
                    prefix = OUTPUT_PREFIX;
                }

                subIdx = 1;
                while (true) {
                    String nodePath = prefix + "_" + subIdx + "/connections";
                    JsonElement inputElement = jsonParser.getJsonElementFromPath(dataElement, nodePath);

                    if (inputElement == null)
                        break;

                    JsonArray inputArray = inputElement.getAsJsonArray();
                    if (inputElement.getAsJsonArray().size() == 0)
                        break;

                    if (subIdx > 1)
                        prefixResult += ",";

                    JsonElement returnElement = jsonParser.getElementFromArrayMember(inputArray, "node");
                    String nodeNumber = returnElement.getAsString();

                    subIdx++;

                    prefixResult += nodeNumber;
                }

                if (prefixType == INPUT_PREFIX_TYPE)
                    layoutInfo.input = prefixResult;
                else
                    layoutInfo.output = prefixResult;
            }

            layoutArray.add(layoutInfo);

            mainIdx++;
        }
        return layoutArray;
    }
}