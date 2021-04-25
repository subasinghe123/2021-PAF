package rest;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Product")
public class ProductREST {
	
	//Product prductObj = new Product();
	Product obj= new Product();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProducts() {
		
		String output="<html>"
				+ "   <head>"
				+ "       <meta charset='utf-8'>"
				+ "       <meta name='viewport' content='width=device-width, initial-scale=1'>"
				+ "       <title>Manage Products</title>"
				+ "       <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl' crossorigin='anonymous'>"
				+ "       <link rel='stylesheet' href='../css/product-list.css'>"
				+ "       <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js' integrity='sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0' crossorigin='anonymous'></script>"
				+ "		  <script src='https://cdn.jsdelivr.net/npm/@popperjs/core@2.6.0/dist/umd/popper.min.js' integrity='sha384-KsvD1yqQ1/1+IA7gi3P0tyJcT3vR+NdBTt13hSJ2lnve8agRGXTTyNaBYmCR/Nwi' crossorigin='anonymous'></script>"
				+ "		  <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.min.js' integrity='sha384-nsg8ua9HAw1y0W1btsyWgBklPnCUAFLuTMS2G72MMONqmOymq585AcH49TLBQObG' crossorigin='anonymous'></script>"
				+ "    	  <script type='text/javascript'></script>"
				+ "   </head>"
				+ "   <body oncontextmenu='return false' class='snippet-body'>"
				+ "       <div class='container-fluid px-1 px-md-5 px-lg-1 px-xl-5 py-5 mx-auto'>"
				+ "    	  <div class='cad card0 border-0'>"
				+ "        	 <div class='row d-flex'>"
				+ "            	<div class='col-lg-10'>"
				+ "                	<div class='card2 card border-0 px-4 py-5'>"
				+ "                		<div class='row mb-4 px-3'>"
				+ "                			<h1 class='mb-5 mr-4 mt-2 left' style='margin-left: -24px;'>Product Management</h1>"
				+ 							obj.readProducts()
				+ "                		</div>"
				+ "            		</div>"
				+ "        	 	</div>"
				+ "      	  </div>"
				+ "   	   </div>"
				+ "   	 </div>"
				+ "   </body>"
				+ "</html>";
		return output;	
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertProduct(@FormParam("name") String name, 
			 @FormParam("price") String price, 
			 @FormParam("category") String category, 
			 @FormParam("stock") String stock,
			 @FormParam("description") String description) {
		
		String output = obj.insertProduct(name, price, category, stock, description); 
		return output; 
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProduct(String productData) {
		
		//Convert the input string to a JSON object 
		 JsonObject itemObject = new JsonParser().parse(productData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String productID = itemObject.get("productID").getAsString(); 
		 String name = itemObject.get("name").getAsString(); 
		 String price = itemObject.get("price").getAsString(); 
		 String category = itemObject.get("category").getAsString(); 
		 String stock = itemObject.get("stock").getAsString(); 
		 String description = itemObject.get("description").getAsString();
		 String output = obj.updateProduct(productID, name, price, category, stock, description); 
		return output; 
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProduct(String productData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(productData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String productID = doc.select("productID").text(); 
	 String output = obj.deleteProduct(productID); 
	return output; 
	}
}
