package com.example.dataparsingexample
/**
 * XML 파싱하는방법 - DOM(OOP방식) , SAX  , Pull
 * JSON 파싱  - JSONObject  와 JSONArray 를 이용
 * **/

import android.os.Bundle
import android.sax.RootElement
import android.util.Xml
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import org.xmlpull.v1.XmlPullParser
import java.io.InputStream
import java.lang.Exception
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() , View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lab2_dom.setOnClickListener(this)
        lab2_sax.setOnClickListener(this)
        lab2_pull.setOnClickListener(this)
        lab2_json.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when {
            v === lab2_dom -> {
                domParsing()
            }
            v === lab2_sax -> {
                saxParsing()
            }
            v === lab2_pull -> {
                pullParsing()
            }
            v === lab2_json -> {
                jsonParsing()
            }
        }
    }

    private fun domParsing() {
        try{
            //파일읽기
            val inputStream = assets.open("test.xml")
            //DOM 파싱
            val factory  = DocumentBuilderFactory.newInstance()
            val builder = factory.newDocumentBuilder()
            val doc =builder.parse(inputStream,null)

            //태그 attribute 값 획득
            val tempElement =doc.getElementsByTagName("temperature").item(0) as org.w3c.dom.Element
            val temperature =tempElement.getAttribute("value")

            val cityElement = doc.getElementsByTagName("city").item(0) as org.w3c.dom.Element
            val city =cityElement.getAttribute("name")

            //출력
            lab2_result_title.text= "Dom Parsing Result "
            lab2_city.text=city
            lab2_temperature.text=temperature

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun saxParsing() {

       lab2_result_title.text="SAX Parsing Result"

        val root = RootElement("current")
        val cityElement =root.getChild("city")
        val tempElement= root.getChild("temperature")

        cityElement.setStartElementListener {
            lab2_city.text= it.getValue("name")
        }

        tempElement.setStartElementListener {
            lab2_temperature.text=it.getValue("value")
        }

        try{
            val inputStream = assets.open("text.xml")
            Xml.parse(inputStream,Xml.Encoding.UTF_8,root.contentHandler)
        }catch(e:Exception){
            e.printStackTrace()
        }
    }

    private fun pullParsing() {
        lab2_result_title.text= "Pull Parsing Result"
        try{
            val inputStream =  assets.open("text.xml")
            val parser = Xml.newPullParser()
            parser.setInput(inputStream,null)
            var eventType = parser.eventType
            val done = false

            while(eventType != XmlPullParser.END_DOCUMENT && !done){
                var name :String?=null
                if(eventType == XmlPullParser.START_TAG){
                    name =parser.name
                    if(name == "city"){
                        lab2_city.text=parser.getAttributeValue(null,"name")
                    }else if(name == "temperature"){
                        lab2_temperature.text=parser.getAttributeValue(null,"value")
                    }
                }
                eventType=parser.next()
            }
        }catch(e:Exception){
            e.printStackTrace()
        }
    }

    private fun jsonParsing() {
        lab2_result_title.text="JSON Parsing Result"
        //파일읽기
        var json :String?= null
        try{
            var si =assets.open("test.json")
            var size =si.available()
            var buffer = ByteArray(size)
            si.read(buffer)
            si.close()
            json=String(buffer)

            val root = JSONObject(json)

            lab2_city.text=root.getString("name")
            val main = root.getJSONObject("main")
            lab2_temperature.text =main.getString("temp")
        }catch(e:Exception){
            e.printStackTrace()
        }
    }
}