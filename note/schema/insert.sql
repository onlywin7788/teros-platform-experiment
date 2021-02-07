INSERT INTO `rep_apim_api_method` (`API_METHOD_ID`, `METHOD_NAME`, `DESCRIPTION`) VALUES
	(1, 'GET', 'GET METHOD'),
	(2, 'POST', 'POST METHOD'),
	(3, 'PUT', 'PUT METHOD'),
	(4, 'DELETE', 'DELETE METHOD');

INSERT INTO `rep_dsm_flow` (`FLOW_ID`, `FLOW_NAME`, `LAYOUT`, `CONFIG_CONTENTS`, `DESCRIPTION`, `CREATE_DTIME`, `MODIFY_DTIME`) VALUES
	(1, 'complex-flow', '{"drawflow":{"Home":{"data":{"2":{"id":2,"name":"KAFKA","data":{},"class":"KAFKA","html":"\\n            <div>\\n                <div class=\\"title-box\\">Kafka</div>\\n            </div>\\n            ","typenode":false,"inputs":{"input_1":{"connections":[]}},"outputs":{"output_1":{"connections":[{"node":"5","output":"input_1"}]}},"pos_x":43,"pos_y":243},"3":{"id":3,"name":"RESTFUL","data":{},"class":"RESTFUL","html":"\\n            <div>\\n                <div class=\\"title-box\\">RestFul</div>\\n            </div>\\n            ","typenode":false,"inputs":{"input_1":{"connections":[]}},"outputs":{"output_1":{"connections":[{"node":"4","output":"input_1"}]}},"pos_x":39,"pos_y":114},"4":{"id":4,"name":"CUSTOM","data":{},"class":"CUSTOM","html":"\\n            <div>\\n                <div class=\\"title-box\\">Custom Node</div>\\n                <div class=\\"box\\">\\n                    <input type=\\"button\\" class=\\"btn btn-xs btn-flat\\" value=\\"설정 \\"></input>\\n                </div>\\n            </div>\\n            ","typenode":false,"inputs":{"input_1":{"connections":[{"node":"3","input":"output_1"}]}},"outputs":{"output_1":{"connections":[{"node":"6","output":"input_1"}]}},"pos_x":294,"pos_y":63},"5":{"id":5,"name":"CUSTOM","data":{},"class":"CUSTOM","html":"\\n            <div>\\n                <div class=\\"title-box\\">Custom Node</div>\\n                <div class=\\"box\\">\\n                    <input type=\\"button\\" class=\\"btn btn-xs btn-flat\\" value=\\"설정 \\"></input>\\n                </div>\\n            </div>\\n            ","typenode":false,"inputs":{"input_1":{"connections":[{"node":"2","input":"output_1"}]}},"outputs":{"output_1":{"connections":[{"node":"6","output":"input_1"}]}},"pos_x":300,"pos_y":234},"6":{"id":6,"name":"ROUTER","data":{},"class":"ROUTER","html":"\\n            <div>\\n                <div class=\\"title-box\\">Router</div>\\n                <div class=\\"box\\">\\n                    <input type=\\"button\\" class=\\"btn btn-xs btn-flat\\" value=\\"룰 지정\\"></input>\\n                </div>\\n            </div>\\n            ","typenode":false,"inputs":{"input_1":{"connections":[{"node":"4","input":"output_1"},{"node":"5","input":"output_1"}]}},"outputs":{"output_1":{"connections":[{"node":"7","output":"input_1"}]},"output_2":{"connections":[{"node":"8","output":"input_1"}]},"output_3":{"connections":[{"node":"9","output":"input_1"}]}},"pos_x":561,"pos_y":160},"7":{"id":7,"name":"KAFKA","data":{},"class":"KAFKA","html":"\\n            <div>\\n                <div class=\\"title-box\\">Kafka</div>\\n            </div>\\n            ","typenode":false,"inputs":{"input_1":{"connections":[{"node":"6","input":"output_1"}]}},"outputs":{"output_1":{"connections":[]}},"pos_x":798,"pos_y":67},"8":{"id":8,"name":"RABBITMQ","data":{},"class":"RABBITMQ","html":"\\n            <div>\\n                <div class=\\"title-box\\">RabbitMQ</div>\\n            </div>\\n            ","typenode":false,"inputs":{"input_1":{"connections":[{"node":"6","input":"output_2"}]}},"outputs":{"output_1":{"connections":[]}},"pos_x":804,"pos_y":187},"9":{"id":9,"name":"SOAP","data":{},"class":"SOAP","html":"\\n            <div>\\n                <div class=\\"title-box\\">SOAP</div>\\n            </div>\\n            ","typenode":false,"inputs":{"input_1":{"connections":[{"node":"6","input":"output_3"}]}},"outputs":{"output_1":{"connections":[]}},"pos_x":809,"pos_y":290}}}}}', '<?xml version="1.0" encoding="UTF-8"?><config>\r\n<properties>\r\n<param key="flow.name" value=""/>\r\n<param key="flow.service.mode" value="online"/>\r\n<param key="flow.service.port" value="38088"/>\r\n</properties>\r\n<flow>\r\n<definition>\r\n<node class="KAFKA" id="KAFKA-02" input="" number="2" output="5"/>\r\n<node class="RESTFUL" id="RESTFUL-03" input="" number="3" output="4"/>\r\n<node class="CUSTOM" id="CUSTOM-04" input="3" number="4" output="6"/>\r\n<node class="CUSTOM" id="CUSTOM-05" input="2" number="5" output="6"/>\r\n<node class="ROUTER" id="ROUTER-06" input="5" number="6" output="7,8,9"/>\r\n<node class="KAFKA" id="KAFKA-07" input="6" number="7" output=""/>\r\n<node class="RABBITMQ" id="RABBITMQ-08" input="6" number="8" output=""/>\r\n<node class="SOAP" id="SOAP-09" input="6" number="9" output=""/>\r\n</definition>\r\n<setting>\r\n<node id="KAFKA-02">\r\n<params>\r\n<param key="component.path" value="com.ext.teros.component.Executor"/>\r\n</params>\r\n</node>\r\n<node id="RESTFUL-03">\r\n<params>\r\n<param key="component.path" value="com.ext.teros.component.Executor"/>\r\n</params>\r\n</node>\r\n<node id="CUSTOM-04">\r\n<params>\r\n<param key="component.path" value="com.ext.teros.component.Executor"/>\r\n</params>\r\n</node>\r\n<node id="CUSTOM-05">\r\n<params>\r\n<param key="component.path" value="com.ext.teros.component.Executor"/>\r\n</params>\r\n</node>\r\n<node id="ROUTER-06">\r\n<params>\r\n<param key="component.path" value="com.ext.teros.component.Executor"/>\r\n</params>\r\n</node>\r\n<node id="KAFKA-07">\r\n<params>\r\n<param key="component.path" value="com.ext.teros.component.Executor"/>\r\n</params>\r\n</node>\r\n<node id="RABBITMQ-08">\r\n<params>\r\n<param key="component.path" value="com.ext.teros.component.Executor"/>\r\n</params>\r\n</node>\r\n<node id="SOAP-09">\r\n<params>\r\n<param key="component.path" value="com.ext.teros.component.Executor"/>\r\n</params>\r\n</node>\r\n</setting>\r\n</flow>\r\n</config>\r\n', 'complex 플로우 테스트', '2021-01-22 10:49:46', '2021-01-22 10:50:08');

INSERT INTO `rep_dsm_flow_class` (`CLASS_ID`, `CLASS_NAME`, `COMPONENT_LOAD`) VALUES
	(1, 'ORACLE', 'com.ext.teros.message_connector.oracle.Executor'),
	(2, 'POSTGRESQL', 'com.ext.teros.message_connector.postgresql.Executor'),
	(3, 'MARIADB', 'com.ext.teros.message_connector.mariadb.Executor'),
	(4, 'KAFKA', 'com.ext.teros.message_connector.kafka.Executor'),
	(5, 'RABBITMQ', 'com.ext.teros.message_connector.rabbitmq.Executor'),
	(6, 'SAP', 'com.ext.teros.message_connector.sap.Executor'),
	(7, 'RESTFUL', 'com.ext.teros.message_connector.restful.Executor'),
	(8, 'SOAP', 'com.ext.teros.message_connector.soap.Executor'),
	(9, 'CUSTOM', 'com.ext.teros.message_connector.custom.Executor'),
	(10, 'TRANSFORM', 'com.ext.teros.message_connector.transform.Executor'),
	(11, 'ROUTER', 'com.ext.teros.message_connector.router.Executor');

INSERT INTO `rep_dsm_flow_class_prop` (`CLASS_PROP_ID`, `PROP_NAME`, `DISPLAY_NAME`, `DESCRIPTION`, `CLASS_ID`) VALUES
	(1, 'connection.host', '호스트', NULL, 1),
	(2, 'connection.port', '포트', NULL, 1),
	(3, 'connection.account.id', '아이디', NULL, 1),
	(4, 'connection.account.password', '패스워드', NULL, 1),
	(5, 'connection.sid', 'DB SID', NULL, 1),
	(6, 'connection.host', '호스트', NULL, 2),
	(7, 'connection.port', '포트', NULL, 2),
	(8, 'connection.account.id', '아이디', NULL, 2),
	(9, 'connection.account.password', '패스워드', NULL, 2),
	(10, 'connection.sid', 'DB SID', NULL, 2),
	(11, 'connection.host', '호스트', NULL, 3),
	(12, 'connection.port', '포트', NULL, 3),
	(13, 'connection.account.id', '아이디', NULL, 3),
	(14, 'connection.account.password', '패스워드', NULL, 3),
	(15, 'connection.sid', 'DB SID', NULL, 3),
	(17, 'connection.test', 'test5', NULL, 5),
	(18, 'connection.test', 'test6', NULL, 6),
	(19, 'connection.test', 'test7', NULL, 7),
	(20, 'connection.test', 'test8', NULL, 8),
	(21, 'connection.test', 'test9', NULL, 9),
	(22, 'connection.test', 'test10', NULL, 10),
	(23, 'connection.test', 'test11', NULL, 11),
	(24, 'connection.host', '호스트', NULL, 4),
	(25, 'connection.port', '포트', NULL, 4),
	(26, 'connection.topic', '토픽명', NULL, 4),
	(27, 'message.contents.format', '메시지 포맷', NULL, 4);

INSERT INTO `rep_appm_app_type` (`TYPE_CODE`, `DESCRIPTION`) VALUES
	('API', 'API APPLICATION'),
	('DATA', 'DATA APPLICATION');
