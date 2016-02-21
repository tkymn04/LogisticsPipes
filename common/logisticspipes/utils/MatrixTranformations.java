package logisticspipes.utils;


import net.minecraft.util.EnumFacing;

public final class MatrixTranformations {

	/**
	 * Deactivate constructor
	 */
	private MatrixTranformations() {}

	/**
	 * Mirrors the array on the Y axis by calculating offsets from 0.5F
	 *
	 * @param targetArray
	 */
	public static void mirrorY(float[][] targetArray) {
		float temp = targetArray[1][0];
		targetArray[1][0] = (targetArray[1][1] - 0.5F) * -1F + 0.5F; // 1 -> 0.5F -> -0.5F -> 0F
		targetArray[1][1] = (temp - 0.5F) * -1F + 0.5F; // 0 -> -0.5F -> 0.5F -> 1F
	}

	/**
	 * Shifts the coordinates around effectively rotating something. Zero state
	 * is DOWN then -> NORTH -> WEST Note - To obtain Position, do a mirrorY()
	 * before rotating
	 *
	 * @param targetArray
	 *            the array that should be rotated
	 */
	public static void rotate(float[][] targetArray) {
		for (int i = 0; i < 2; i++) {
			float temp = targetArray[2][i];
			targetArray[2][i] = targetArray[1][i];
			targetArray[1][i] = targetArray[0][i];
			targetArray[0][i] = temp;
		}
	}

	/**
	 * @param targetArray
	 *            the array that should be transformed
	 * @param direction
	 */
	public static void transform(float[][] targetArray, EnumFacing direction) {
		if ((direction.ordinal() & 0x1) == 1) {
			MatrixTranformations.mirrorY(targetArray);
		}

		for (int i = 0; i < (direction.ordinal() >> 1); i++) {
			MatrixTranformations.rotate(targetArray);
		}
	}

	/**
	 * Clones both dimensions of a float[][]
	 *
	 * @param source
	 *            the float[][] to deepClone
	 */
	public static float[][] deepClone(float[][] source) {
		float[][] target = source.clone();
		for (int i = 0; i < target.length; i++) {
			target[i] = source[i].clone();
		}
		return target;
	}
}
