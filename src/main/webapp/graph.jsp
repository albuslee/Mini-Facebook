<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="edu.unsw.minifacebook.bean.GraphBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>graph</title>
    <style type="text/css">
        html, body, #main { height: 100%; width: 100%; margin: 0; padding: 0 }
    </style>
</head>
<body>
		   <s:form style="float:right" class="form-inline navbar-form" action="graphSearch">
				<div class="input-group">
                   <s:textfield name="detailform.name" type="text" class="form-control" placeholder="freindSearch"></s:textfield>
                    <span class="input-group-btn">
                     <s:submit value="search" class="btn btn-default"></s:submit>
                    </span>
                  </div>
		   </s:form>
    <div id="main" style="height: 500px; width: 500px; margin: 0; padding: 0"></div>
    <div style="displat:none" id="jsonData"><%=request.getSession().getAttribute("graphlist")%></div>
</body>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="echarts.min.js"></script>
    <script type="text/javascript">
    
    function draw(){
    var myChart = echarts.init(document.getElementById('main'));
    var array=eval('(' + $('#jsonData').html() + ')');
    option = {
            title: { text: 'relationship graph' },
            tooltip: {
                formatter: function (x) {
                    return x.data.des;
                }
            },
            series: [
                {
                    type: 'graph',
                    layout: 'force',
                    symbolSize: 80,
                    roam: true,
                    edgeSymbol: ['circle', 'arrow'],
                    edgeSymbolSize: [4, 10],
                    edgeLabel: {
                        normal: {
                            textStyle: {
                                fontSize: 20
                            }
                        }
                    },
                    force: {
                        repulsion: 2500,
                        edgeLength: [10, 50]
                    },
                    draggable: true,
                    itemStyle: {
                        normal: {
                            color: '#4b565b'
                        }
                    },
                    lineStyle: {
                        normal: {
                            width: 2,
                            color: '#4b565b'

                        }
                    },
                    edgeLabel: {
                        normal: {
                            show: true,
                            formatter: function (x) {
                                return x.data.name;
                            }
                        }
                    },
                    label: {
                        normal: {
                            show: true,
                            textStyle: {
                            }
                        }
                    },
                    data: [
                        {
                            name: 'p1',
                            des: '1',
                            symbolSize: 50,
                            itemStyle: {
                                normal: {
                                    color: 'red'
                                }
                            }
                        },{
                            name: 'p2',
                            des: '2',
                            symbolSize: 50
                        }
                    ],
                    links: array
                }
            ]
        };
    myChart.setOption(option);
    }
    draw();
    </script>

</html>