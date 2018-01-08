package API;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;

public class class1 {
public static String APIkey="AIzaSyCX4weFIkNsLV1inGmQbEeQV33Tr1_ueS8";

public static void main(String[] args){
	//Replace the API key below with a valid API key.
	GeoApiContext context = new GeoApiContext().setApiKey(APIkey);
	GeocodingResult[] results;
	try {
		results = GeocodingApi.geocode(context,
		 "Brown University Providence").await();
		double sourcelat= results[0].geometry.location.lat;
		double sourcelng= results[0].geometry.location.lng;
		
		DistanceMatrix results2 = notify(sourcelat, sourcelng, "Providence Amtrak");
		System.out.println(results2.rows[0].elements[0].distance.inMeters);
		System.out.println(results2.rows[0].elements[0].duration.inSeconds);
		
		  
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static DistanceMatrix notify(double sourcelat, double sourcelng, String destination) throws Exception {
	DistanceMatrix results2 = DistanceMatrixApi.getDistanceMatrix(new GeoApiContext().setApiKey(APIkey), new String []{sourcelat +"," +sourcelng},
			new String [] {destination}).await();
	if (results2.rows[0].elements[0].distance.inMeters < 150 ){
		System.out.println("Now Approaching Destination");
	}
	return results2;
}
}
