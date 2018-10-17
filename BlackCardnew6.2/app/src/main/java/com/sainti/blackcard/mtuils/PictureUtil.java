package com.sainti.blackcard.mtuils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PictureUtil {
	/**
	 * 图片压缩-质量压缩
	 *
	 * @param filePath 源图片路径
	 * @return 压缩后的路径
	 */

	public static String compressImage(String filePath) {

		//原文件
		File oldFile = new File(filePath);


		//压缩文件路径 照片路径/
		String targetPath = oldFile.getPath();
		int quality = 100;//压缩比例0-100
		Bitmap bm = getSmall(filePath);//获取一定尺寸的图片
		int degree = getRotateAngle(filePath);//获取相片拍摄角度

		if (degree != 0) {//旋转照片角度，防止头像横着显示
			bm = setRotateAngle(degree, bm);
		}
		File outputFile = new File(targetPath);
		try {
			if (!outputFile.exists()) {
				outputFile.getParentFile().mkdirs();
				//outputFile.createNewFile();
			} else {
				outputFile.delete();
			}
			FileOutputStream out = new FileOutputStream(outputFile);
			bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return filePath;
		}
		return outputFile.getPath();
	}

	/**
	 * 旋转图片角度
	 *
	 * @param angle
	 * @param bitmap
	 * @return
	 */
	public static Bitmap setRotateAngle(int angle, Bitmap bitmap) {

		if (bitmap != null) {
			Matrix m = new Matrix();
			m.postRotate(angle);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), m, true);
			return bitmap;
		}
		return bitmap;

	}


	/**
	 * 获取图片的旋转角度
	 *
	 * @param filePath
	 * @return
	 */
	public static int getRotateAngle(String filePath) {
		int rotate_angle = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(filePath);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					rotate_angle = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					rotate_angle = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					rotate_angle = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rotate_angle;
	}

	/**
	 * 根据路径获得图片信息并按比例压缩，返回bitmap
	 */
	public static Bitmap getSmall(String filePath) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
		Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
		options.inJustDecodeBounds = false;
		int w = options.outWidth;
		int h = options.outHeight;
		float hh = 800f;
		float ww = 480f;
		int be = 1;
		if (w > h && w > ww) {
			be = (int) (options.outHeight / ww);
		} else {
			be = (int) (options.outHeight / hh);
		}
		if (be <= 0) {
			be = 1;
		}
		options.inSampleSize = be;
		bitmap = BitmapFactory.decodeFile(filePath, options);
		return copmproessImage(bitmap);
	}

	private static Bitmap copmproessImage(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		int options = 100;
		while (baos.toByteArray().length / 1024 > 100) {
			baos.reset();
			bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
			options -= 10;
		}

		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bitmap1 = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap1;
	}
}
