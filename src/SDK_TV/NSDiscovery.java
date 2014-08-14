package SDK_TV;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.cybergarage.upnp.ControlPoint;
import org.cybergarage.upnp.device.SearchResponseListener;
import org.cybergarage.upnp.ssdp.SSDPPacket;
import org.cybergarage.xml.Node;
import org.cybergarage.xml.ParserException;
import org.cybergarage.xml.parser.JaxpParser;

import android.util.Log;

import com.iansd.Util;

public class NSDiscovery {

	private static final String DEBUG_TAG		= "NSDiscovery";

	public static final String UPNP_NSCREEN_FILTER	= "urn:samsung.com:service:MultiScreenService:1";//"urn:samsung.com:service:MultiScreenService:1";
	
	private NSConnection		connection;
	private ControlPoint		controlPoint;
	private NSDiscoveryListener listener;
	
	public NSDiscovery(NSConnection connection){	
		this.connection		= connection;
		this.controlPoint 	= new ControlPoint();		
	}

	public void search(){		
		this.dispose();

        Log.d(NSDiscovery.DEBUG_TAG, "search");

		this.controlPoint = new ControlPoint();
		
		if (this.listener != null){
			this.controlPoint.removeSearchResponseListener(this.listener);
		}
		
		if (this.connection.isWifiConnected()){
			this.listener = new NSDiscoveryListener(this.connection);		
			this.controlPoint.addSearchResponseListener(this.listener);
			this.controlPoint.start(NSDiscovery.UPNP_NSCREEN_FILTER);	
		}
	}

	public void dispose(){
		this.controlPoint.stop();
		this.controlPoint.unlock();
		this.controlPoint.unsubscribe();
		this.controlPoint.removeSearchResponseListener(this.listener);
		this.controlPoint.finalize();
	}
	
	private class NSDiscoveryListener implements SearchResponseListener {		
		
		private NSConnection connection;

		public NSDiscoveryListener(NSConnection connection){
			this.connection = connection;
		}
	
		public Node getDeviceNode(HttpResponse response) throws IllegalStateException, ParserException, IOException{
			return new JaxpParser().parse(response.getEntity().getContent()).getNode("device");
		}

		public void deviceSearchResponseReceived(SSDPPacket ssdpPacket){
			try{
				String location 	= ssdpPacket.getLocation();
				String ipAddress	= ssdpPacket.getRemoteAddress();
				
				Log.d(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "UPnP Device Found");
				Log.d(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "-- Location: " + location);
				Log.d(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "-- Address: " + ipAddress);
				
				//DefaultHttpClient 	client 		= NSConnection.createHttpClient();
                DefaultHttpClient client = new DefaultHttpClient();
				HttpGet 			request 	= new HttpGet(location);
				HttpResponse 		response	= client.execute(request);
				
				Log.d(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "UPnP Device (" + ipAddress + ") Response: " + String.valueOf(response.getStatusLine().getStatusCode()));
				Log.d(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "-- " + response.getStatusLine().getReasonPhrase());
				
				if (response != null && response.getStatusLine().getStatusCode() == 200){

                    if(true)
                    {
                        Util.log("All Headers-----------------------------");
                        Header[] allHeaders = response.getAllHeaders();
                        for (Header header : allHeaders)
                        {
                            Util.log(header.getName() + " : "  + header.getValue());
                        }
                        Util.log("--------------------------------");
                    }

                    Header[] headers = response.getHeaders("Application-URL");

                    if(headers != null && headers.length > 0) {
                        //Find real application URL with port
                        Log.d(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener","Application-URL: " + headers[0].getValue());
                    }
                    else
                    {
                        Log.d(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "unable to get Application-URL from header");
                    }

					Node devNode = this.getDeviceNode(response);					
					if (devNode != null){
						String deviceName 	= devNode.getNodeValue("friendlyName");
						this.connection.addDevice(new NSDevice(ipAddress, deviceName));						
						Log.d(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "UPnP Device (" + ipAddress + ") Recognized as " + deviceName);
					}
					else{
						Log.d(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "UPnP Device (" + ipAddress + ") Not Recognized");
					}
					
					response.getEntity().consumeContent();
				}
				else{
					Log.w(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "UPnP Device (" + ipAddress + ") Unreachable");
				}
			}
			catch (ClientProtocolException e){
				Log.e(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "ClientProtocolException: " + e.getMessage());
			}	
			catch (ParserException e){
				Log.e(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "ParserException: " + e.getMessage());
			}
			catch (IOException e){
				Log.e(NSDiscovery.DEBUG_TAG + ".NSDiscoveryListener", "IOException: " + e.getMessage());
			}
		}					
	}	
}
