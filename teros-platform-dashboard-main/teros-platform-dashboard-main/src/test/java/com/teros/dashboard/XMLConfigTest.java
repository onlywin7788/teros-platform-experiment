package com.teros.dashboard;

import com.google.gson.*;
import com.teros.ext.common.file.CommonFile;
import com.teros.ext.common.parser.JsonParser;
import com.teros.ext.common.parser.XmlParser;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import java.util.ArrayList;


class JsonParser2 {


    Gson gson = null;
    JsonElement json = null;

    public JsonParser2() {
        this.gson = (new GsonBuilder()).disableHtmlEscaping().setPrettyPrinting().create();
    }

    public void loadString(String contents) {
        JsonObject jsonObject = (JsonObject) this.gson.fromJson(contents, JsonObject.class);
        this.json = jsonObject;
    }

    public String getJsonString()
    {
        String result = this.json.toString();
        return result;
    }

    public String getTextFromElement(String path) throws Exception {
        String returnString = "";
        JsonElement jsonElement = this.getJsonElementFromPath(path);
        returnString = jsonElement.getAsString();
        if (returnString == null) {
            returnString = "";
        }

        return returnString;
    }

    public JsonElement getJsonElementFromPath(String path) throws Exception {
        String[] parts = path.split("\\/|\\[|\\]");
        JsonElement returnElement = this.json;
        String[] var4 = parts;
        int var5 = parts.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            String key = var4[var6];
            key = key.trim();
            if (!key.isEmpty()) {
                if (returnElement == null) {
                    returnElement = JsonNull.INSTANCE;
                    break;
                }

                if (((JsonElement) returnElement).isJsonObject()) {
                    returnElement = ((JsonObject) returnElement).get(key);
                } else {
                    if (!((JsonElement) returnElement).isJsonArray()) {
                        break;
                    }

                    int ix = Integer.valueOf(key) - 1;
                    returnElement = ((JsonArray) returnElement).get(ix);
                }
            }
        }

        if(returnElement != null)
            if(returnElement.isJsonNull())
                returnElement = null;

        return (JsonElement) returnElement;
    }


    public JsonElement getJsonElementFromPath(JsonElement acquireElement, String path) throws Exception {
        String[] parts = path.split("\\/|\\[|\\]");
        JsonElement returnElement = acquireElement;

        if (returnElement == null)
            returnElement = this.json;

        String[] var4 = parts;
        int var5 = parts.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            String key = var4[var6];
            key = key.trim();
            if (!key.isEmpty()) {
                if (returnElement == null) {
                    returnElement = JsonNull.INSTANCE;
                    break;
                }

                if (((JsonElement) returnElement).isJsonObject()) {
                    returnElement = ((JsonObject) returnElement).get(key);
                } else {
                    if (!((JsonElement) returnElement).isJsonArray()) {
                        break;
                    }

                    int ix = Integer.valueOf(key) - 1;
                    returnElement = ((JsonArray) returnElement).get(ix);
                }
            }
        }

        if(returnElement != null)
            if(returnElement.isJsonNull())
                returnElement = null;

        return (JsonElement) returnElement;
    }

    public JsonElement getElementFromArrayMember(JsonArray acquireJsonArray, String memberName) {
        JsonArray jsonArray = acquireJsonArray;
        JsonElement returnElement = null;

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            JsonElement element = jsonObject.get(memberName);
            if (element != null)
                returnElement = element;
        }
        return returnElement;
    }

    public JsonArray addJsonArray(JsonArray acquireJsonArray, JsonObject jsonObject) {
        JsonArray jsonArray = acquireJsonArray;

        try {
            if (jsonArray == null) {
                jsonArray = new JsonArray();
            }

            jsonArray.add(jsonObject);
            return jsonArray;
        } catch (Exception var5) {
            throw var5;
        }
    }

    public JsonObject addJsonObject(JsonObject acquireJsonObject, String key, JsonElement jsonElement) {
        JsonObject jsonObject = acquireJsonObject;

        try {
            if (jsonObject == null) {
                jsonObject = new JsonObject();
            }

            jsonObject.add(key, jsonElement);
            return jsonObject;
        } catch (Exception var6) {
            throw var6;
        }
    }
}


class LayoutInfo {
    public String id = "";
    public String type = "";
    public String input = "";
    public String output = "";
}


public class XMLConfigTest {

    @Test
    public void test() throws Exception {

        try {
            ArrayList<LayoutInfo> layoutList;
            CommonFile commonFile = new CommonFile();

            byte[] readBytes = commonFile.readFile("e:/teros_home/test/layout.json");
            String contents = new String(readBytes);

            JsonParser2 jsonParser = new JsonParser2();
            jsonParser.loadString(contents);

            String result = jsonParser.getJsonString();

            System.out.println(result);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
//        layoutList = getLayoutInfo(contents);

    }

    public void getConfigContents() {
        String result = "";
        Element rootElement = null;
        Element propElement = null;
        Element flowElement = null;
        Element definitionElement = null;
        Element settingElement = null;

        try {
            XmlParser xmlParser = new XmlParser();
            xmlParser.createXmlDocument();

            rootElement = xmlParser.createElement(null, "config", "");

            // 1 depth
            propElement = xmlParser.createElement(rootElement, "properties", "");
            flowElement = xmlParser.createElement(rootElement, "flow", "");

            // 2 depth
            definitionElement = xmlParser.createElement(flowElement, "definition", "");
            settingElement = xmlParser.createElement(flowElement, "setting", "");

            result = xmlParser.getDocumentString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    public ArrayList<LayoutInfo> getLayoutInfo(String contents) throws Exception {

        String INPUT_PREFIX = "inputs/input";
        String OUTPUT_PREFIX = "outputs/output";

        ArrayList<LayoutInfo> layoutArray = new ArrayList<LayoutInfo>();

        JsonParser2 jsonParser = new JsonParser2();
        jsonParser.loadString(contents);

        int mainIdx = 1;
        int subIdx = 1;

        while (true) {
            JsonElement dataElement = jsonParser.getJsonElementFromPath("drawflow/Home/data/" + mainIdx);
            if (dataElement == null)
                break;

            LayoutInfo layoutInfo = new LayoutInfo();

            String nodeType = jsonParser.getJsonElementFromPath(dataElement, "name").getAsString();
            layoutInfo.id = mainIdx + "";
            layoutInfo.type = nodeType;

            // input element

            for(int i=0; i<2; i++) {

                String prefix = INPUT_PREFIX;
                if(i > 0)
                    prefix = OUTPUT_PREFIX;

                subIdx = 1;
                while (true) {
                    JsonElement inputElement = jsonParser.getJsonElementFromPath(dataElement, prefix + "_" + subIdx + "/connections");

                    if (inputElement == null)
                        break;

                    JsonArray inputArray = inputElement.getAsJsonArray();
                    if (inputElement.getAsJsonArray().size() == 0)
                        break;

                    JsonElement returnElement = jsonParser.getElementFromArrayMember(inputArray, "node");
                    String nodeNumber = returnElement.getAsString();

                    subIdx++;

                    System.out.println(nodeNumber);
                }
            }

            mainIdx++;
        }


        /*
        JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();

        for (int fieldIdx = 0; fieldIdx < jsonArray.size(); fieldIdx++) {
            String json = jsonArray.get(fieldIdx).getAsString();
        }
        */


        return null;

    }
}
