<%@ page import="com.dcu.box.Selectedinput"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="ISO-8859-1">
<title>Analytics</title>
</head>
<body>
	<%
		Map<Integer, String> listOfItemsSelected = Selectedinput.listOfItems;
		String selectItemsId = "";
		for (Map.Entry<Integer, String> pair : listOfItemsSelected.entrySet()) {
			selectItemsId = selectItemsId + pair.getValue()  + "|" ;
		}

		selectItemsId = selectItemsId.substring(0, selectItemsId.length()-1);
		System.out.println(selectItemsId);
	%>
	<h2>
		See Analytics here for
		<%=listOfItemsSelected.size()%>
		items you have selected
	</h2>
	<%
		Selectedinput.listOfItems.clear();
	%>
	<center>
		<input type="button" onclick="location.href='Check.jsp';"
			value="Thanks" />
	</center>
	
	<div id="vizContainer" style="width: 1200px; height: 600px;  padding: 10px;" align="center"></div>
	
<script type="text/javascript"
	src="https://eu-west-1a.online.tableau.com/javascripts/api/tableau-2.min.js"></script>
<script type="text/javascript">
    function initViz() {
	var viz=null; 
	var containerDiv=null;
	value = "<%= selectItemsId %>";
        containerDiv = document.getElementById("vizContainer"),
            url = "https://eu-west-1a.online.tableau.com/t/food/views/Book1/Dashboard1?iframeSizedToWindow=true&:embed=y&:showAppBanner=false&:display_count=no&:showVizHome=no",
            options = {
                    onFirstInteractive: function () {
                    console.log("Run this code when the viz has finished loading.");
                    mainWorkbook = viz.getWorkbook().changeParameterValueAsync('Food', value);
                }
            }; // end options
        // Create a viz object and embed it in the container div.
        viz = new tableau.Viz(containerDiv, url, options);
    } // end initViz
    </script> 
    <script type="text/javascript">initViz()</script>
</body>
</html>