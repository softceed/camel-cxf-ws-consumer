package de.softceed.camel.example.toexternal;

import java.util.HashMap;

import org.apache.camel.Exchange;

import de.softceed.ws.examplewebservice.Create;

/**
 * Preparing messages for sending them to Coba web service
 */
public class ToExternalMessageProcessor {

  /** Types of requests to external web service */
  private enum RequestType {
    CREATE
  };

  /**
   * Constructor
   */
  public ToExternalMessageProcessor() throws Exception {
  }

  /**
   * Create data for sending SOAP request, including setting header "SOAPAction" in exchange.
   * 
   * @param exchange Exchange
   * @return JAXB object
   */
  public Object prepareSoapRequest(Exchange exchange) throws Exception {
    String operationName = null;
    String soapActionName = null;
    Object result = exchange.getIn().getBody();
    RequestType type = getRequestType(exchange.getIn().getBody());
    if (type.equals(RequestType.CREATE)) {
      operationName = "create";
      soapActionName = "Interface_ExampleWebService_Binder_create";
    }
    exchange.getIn().setHeader("operationName", operationName);
    exchange.getIn().setHeader("SOAPAction", soapActionName);

    return result;
  }

  public void checkSyncResponseForHttpError(Exchange exchange) throws Exception {
    if (exchange != null && exchange.getIn() != null && exchange.getIn().getHeaders() != null) {
      @SuppressWarnings("unchecked")
      HashMap<String, Object> responseContext = (HashMap<String, Object>) exchange.getIn().getHeader("responsecontext");
      System.out.println("HTTP status code: " + responseContext.get("org.apache.cxf.message.Message.RESPONSE_CODE"));
    }
  }

  /**
   * Get type of request to Coba
   * 
   * @param body Message body, JAXB object
   * @return Type of Request
   */
  private RequestType getRequestType(Object body) {
    RequestType result = null;
    if (body instanceof Create) {
      result = RequestType.CREATE;
    }
    return result;
  }

}
