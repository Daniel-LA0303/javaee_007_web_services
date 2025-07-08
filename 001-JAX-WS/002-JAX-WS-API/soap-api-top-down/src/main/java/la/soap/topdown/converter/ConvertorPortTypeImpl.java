package la.soap.topdown.converter;

import jakarta.jws.WebService;
import la.soap.topdown.ws.BinaryToDecimalRequest;
import la.soap.topdown.ws.BinaryToDecimalResponse;
import la.soap.topdown.ws.ConvertorPortType;
import la.soap.topdown.ws.DecimalToBinaryRequest;
import la.soap.topdown.ws.DecimalToBinaryResponse;

@WebService(
    endpointInterface = "la.soap.topdown.ws.ConvertorPortType",
    serviceName = "ConvertorService"
)
public class ConvertorPortTypeImpl implements ConvertorPortType {

    @Override
    public BinaryToDecimalResponse binaryToDecimal(BinaryToDecimalRequest parameters) {
        String binary = parameters.getBinary();
        int decimal = 0;
        int power = 0;

        for (int i = binary.length() - 1; i >= 0; i--) {
            char bit = binary.charAt(i);
            if (bit == '1') {
                decimal += Math.pow(2, power);
            }
            power++;
        }

        BinaryToDecimalResponse response = new BinaryToDecimalResponse();
        response.setResult(decimal);
        return response;
    }

    @Override
    public DecimalToBinaryResponse decimalToBinary(DecimalToBinaryRequest parameters) {
        int decimal = parameters.getDecimal();
        StringBuilder binary = new StringBuilder();

        if (decimal == 0) {
            binary.append("0");
        } else {
            while (decimal > 0) {
                binary.insert(0, decimal % 2);
                decimal = decimal / 2;
            }
        }

        DecimalToBinaryResponse response = new DecimalToBinaryResponse();
        response.setResult(binary.toString());
        return response;
    }
}
