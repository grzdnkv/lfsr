import java.io.File;
import java.math.BigInteger;
import java.util.List;

public class Main {

	public static void main(String[] args) {
//		String sid = args[0];
//
//		LFSR lfsr = null;
//		List<String> list = null;
//
//		lfsr = new LFSR(sid);
//		list = lfsr.generatePseudoRandomSequences();
//		lfsr.writeLFSROutput(list, ".");
		Geffe gen = new Geffe();
		String[] seqs = new String[3];
		seqs[0] = gen.readGeffeInput("1.txt");
		seqs[1] = gen.readGeffeInput("2.txt");
		seqs[2] = gen.readGeffeInput("3.txt");

		String Out = gen.GeffeGenerator(seqs[0], seqs[2], seqs[1]);
		//gen.writeGeffeOutput(Out);
		//System.out.println();
		byte[] bytes = new BigInteger(Out, 2).toByteArray();
		double[] autoCorrelation = Correlation.autoCorrelation(bytes);
		StringBuilder autoCorrelationResult = new StringBuilder();
		for (int i = 0; i < autoCorrelation.length; i++) {
			autoCorrelationResult.append(i).append(" ").append(autoCorrelation[i]).append("\n");
		}
		Correlation.writeTxtBytes(autoCorrelationResult.toString().getBytes(), "mOut.txt");
	}
}