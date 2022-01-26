package bitoperatoren;

public class Bitoperatoren {

	public final static byte EXECUTEOTHER = 1, WRITEOTHER = 2, READOTHER = 4,
			EXECUTEGROUP = 1, WRITEGROUP = 2, READGROUP = 4, 
			EXECUTEUSER = 1, WRITEUSER = 2, READUSER = 4;

	private String access;
	private byte permissionBits;

	public Bitoperatoren(String access, byte permissionbits) {

		this.access = access;
		this.permissionBits = permissionbits;
	}

	public String getUser() {
		return access;
	}

	public boolean enthaeltBits(byte testBits) {
		if ((permissionBits & testBits) == testBits) {
			return true;
		} else {
			return false;
		}
	}

	public void bitsHinzu(byte bits) {
		permissionBits |= bits;
	}

	public void bitsEntfernen(byte bits) {
		permissionBits &= ~bits;
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder(100);

		switch (permissionBits) {
		case 0 -> System.out.print("---  ");
		case 1 -> System.out.print("--X  ");
		case 2 -> System.out.print("-W-  ");
		case 3 -> System.out.print("-WX  ");
		case 4 -> System.out.print("R--  ");
		case 5 -> System.out.print("R-X  ");
		case 6 -> System.out.print("RW-  ");
		case 7 -> System.out.print("RWX  ");
				
		default ->
		throw new IllegalArgumentException("Unexpected value: " + permissionBits);
		}

		return buffer.toString();
	}

}
