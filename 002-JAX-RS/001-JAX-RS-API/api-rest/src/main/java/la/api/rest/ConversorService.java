package la.api.rest;

public class ConversorService {

	public String decimalABinario(int decimal) {
        return Integer.toBinaryString(decimal);
    }
    
    public int binarioADecimal(String binario) {
        return Integer.parseInt(binario, 2);
    }
	
}
