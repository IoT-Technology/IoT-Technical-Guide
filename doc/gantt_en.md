```mermaid
gantt
	dateFormat YYYY-MM-DD
	title IOT Technical Guide
	
	section IOT in full bloom
	Things platform_en?    :done,    des1, 2019-04-15,2019-04-16
	Things platform_cn?    :done,    des1, 2019-04-15,2019-04-16
	
	section Device access and management
    TSL code               :done,    des1, 2019-04-15,2019-04-16
    TSL docu               :active,    des2, 2019-04-16, 4d
    HTTP code              :    des3, after des2, 3d
    HTTP docu              :    des4, after des3, 4d
    MQTT code              :    des5, after des4, 3d
    MQTT docu              :    des6, after des5, 4d
    Coap code              :    des7, after des6, 3d
    Coap docu              :    des8, after des7, 4d
    
    section expecting
    expecting			   :    des1, 2019-05-27,2019-6-02

	
	
	
    
```