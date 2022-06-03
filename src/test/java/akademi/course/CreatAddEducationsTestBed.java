package akademi.course;

import akademi.commonClass.AbstractSeleniumTest;
import akademi.commonClass.RandomString;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileReader;
import java.util.*;

public class CreatAddEducationsTestBed extends AbstractSeleniumTest {

    protected List<String> namesOfFiles = new ArrayList<String>();
    protected boolean exception = false;

    public CreatAddEducationsTestBed(String type) {
        super(type);
    }

    public void run() {

//Git deneme111


       //runCreatEducations("");
        getJsonFileNames();
        for (int i = 7; i < namesOfFiles.size(); i++)
        {
            runCreatEducations(namesOfFiles.get(i));
            try{
                    Thread.sleep(5000);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            if(exception == true)
                break;
                runCreatEducations("z_exit.json");
        }
    }
    public void runCreatEducations(String courseType){
        System.out.println(courseType);
        try(FileReader AddEducation = new FileReader(ClassLoader.getSystemResource(courseType).getFile())) {
            JSONParser jsonParserAddEducation = new JSONParser();
            JSONObject jsonFile = (JSONObject) jsonParserAddEducation.parse(AddEducation);
            String endpoint = (String) jsonFile.get("endpoint");

            //warmpup
            WebDriver d = getDriver(endpoint);
            //
            JSONArray stages = (JSONArray) jsonFile.get("tasks") ;
            stages.forEach(s->{
                JSONObject stage = (JSONObject) s;
                String name = (String) stage.get("name");
                String type = (String) stage.get("type");
                String xpath = (String) stage.get("xpath");
                String value = (String) stage.get("value");
                String script = (String) stage.get("script");
                String random = (String) stage.get("Random");


                if( random != null )
                {
                    RandomString rndString = new RandomString(5);
                    value = rndString.nextString();
                }

                System.out.println("Executing " + name);
                switch(type){
                    case "sleep":
                        sleep(Integer.valueOf(value));
                        break;
                    case "click":
                        WebElement element = super.updateClickableElement(endpoint ,xpath,true);
                      //  element.
                        break;
                    case "sendKeys":
                        super.updateElement(endpoint,xpath,true,false,value,false);
                        break;
                    case "executeScript" :
                        super.executeScript(endpoint,script,value);
                        break;
                    case "changeURL":
                        super.changeURL(endpoint,value);
                        break;
                    case "clearAndSendKeys" :
                        super.updateElement(endpoint,xpath,false,true, value,false);
                        break;
                    case "backspace" :
                        super.updateElement(endpoint,xpath,true,true, value,true);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("null");
            this.exception = true;
        }

    }
    public static void main(String[] args){
        new CreatAddEducationsTestBed("chrome").run();
    }

    public void   getJsonFileNames()
    {
        File folder = new File("C:\\Users\\mirackasap\\Desktop\\akademi-master\\src\\test\\resources");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++)
        {
            namesOfFiles.add(listOfFiles[i].getName());
        }

        Collections.sort(namesOfFiles);

    }

}

