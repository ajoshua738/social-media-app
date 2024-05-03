package com.example.socialmediaapp.helper;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.Log;
import android.util.Patterns;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    public static final String TAG = "CommonUtil";
    private static final String PREFERENCE_USER_DATA = "merchantdata";

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public final static String YYYYMMDDHHMM = "yyyy/MM/dd hh:mm";
    public final static String YYYYMMDDHHMMA = "yyyy/MM/dd hh:mm a";
    public final static String MMDDYYYYHHMMSS = "MM/dd/yyyy hh:mm:ss a";
    public final static String DDMMYYYYHHMMSS = "dd/MM/yyyy hh:mm:ss a";
    public final static String DDMMMYYYYHHMMSS = "dd-MMM-yyyy HH:mm:ss";
    public final static String DDMMMYYYYHHMMA = "dd MMM yyyy hh:mm a";
    public final static String DATEFORMAT = "MM/dd/yyyy";
    public final static String YYYYMMDD = "yyyyMMdd";
    public final static String DDMMYYYY = "dd/MM/yyyy";
    public final static String DDMMYYYY_ = "dd-MM-yyyy";
    public final static String YYYYMMDD_ = "yyyy-MM-dd";
    public final static String DDMMYYYY_DAY = "dd-MM-yyyy (EEEE)";
    public final static String EEE_MMDD = "EEE MM/dd";
    public final static String MMM_DD_YYYY = "MMM dd, yyyy";
    public final static String _YYYYMMDDTHHMMSS = "yyyy-MM-dd'T'HH:mm:ss";
    public final static String YYMMDD = "yyMMdd";
    public final static String TIME_ELAPSE_FORMAT = "HH 'hours', mm 'mins,' ss 'seconds'";
    public final static String HHMMSSA = "hh:mm:ss a";
    public final static String HHMM = "HH:mm";
    public final static String HHMMA = "hh:mm a";
    public final static String EEE_DDMMMYYYY = "EEE, dd MMM yyyy";
    public final static String DDMMMYY = "dd MMM yy";
    public final static String DDMMMYYYY = "dd MMM yyyy";
    public final static String DDMMM = "dd MMM";
    public final static String YYYY = "yyyy";
    public final static String DD = "dd";
    public final static String MM = "MM";

    public static DecimalFormat DECIMAL_DISPLAY = new DecimalFormat("######0.00");

    public static String formatBigDecimalDisplay (String text) {

        if(text !=null && text.trim().length() > 0 ) {
            BigDecimal value = new BigDecimal(text);
            return DECIMAL_DISPLAY.format(value);
        } else return "";
    }


    // Re-format a given date string
    public static String formatDate(String inputDateString,
                                    String inputDateFormat, String outputDateFormat) {
        String newDateStr = null;
        //Log.d("Transaction123", "DateObject: " +inputDateString);
        //Log.d("Transaction1234", "DateObject2: " +inputDateFormat);
        //Log.d("Transaction1235", "DateObject3: " +outputDateFormat);

        try {

            SimpleDateFormat curFormater = new SimpleDateFormat(inputDateFormat, Locale.ENGLISH);
            //Log.d("Transacti", "DateObject3: " +outputDateFormat);
            Date dateObj = curFormater.parse(inputDateString);
            //Log.d("Transact", "DateObject3: " +outputDateFormat);
            SimpleDateFormat postFormater = new SimpleDateFormat(
                    outputDateFormat, Locale.ENGLISH);
            //Log.d("Transa", "DateObject3: " +outputDateFormat);
            newDateStr = postFormater.format(dateObj);

        } catch (ParseException e) {
            //Log.d("Transaction", "DateObject: " + e.getMessage()+inputDateString);
        }
        return newDateStr;
    }

    // Re-format a given date string
    public static String formatDateRedemption(String inputDateString,
                                              String inputDateFormat, String outputDateFormat) {
        String newDateStr = null;
        try {
            SimpleDateFormat sourceFormat = new SimpleDateFormat(inputDateFormat, Locale.ENGLISH);
            sourceFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            Date parsed = sourceFormat.parse(inputDateString);

            TimeZone tz = TimeZone.getDefault();
            SimpleDateFormat destFormat = new SimpleDateFormat(outputDateFormat, Locale.ENGLISH);
            destFormat.setTimeZone(tz);
            newDateStr = destFormat.format(parsed);




        } catch (ParseException e) {
            //Log.d("Transaction", "DateObject: " + e.getMessage());
        }
        return newDateStr;
    }
    public static String getTodaydate(String fmt) {
        Date date = new Date();
        return new SimpleDateFormat(fmt).format(date);
    }
    public static String getTodayDate() {
        Date date = new Date();
        return new SimpleDateFormat(YYYYMMDD_).format(date);
    }
    public static String getCurrentTimestamp() {
        Date date = new Date();
        return formatDate(new Timestamp(date.getTime()).toString(),
                // "yyyy-MM-dd HH:mm:ss.SSSSSSSSSS", "yyyyMMddHHmmssSS")
                //return formatDate(new Date(System.currentTimeMillis()).toString(),
                "yyyy-MM-dd HH:mm:ss.SSSSSSSSSS", "yyyyMMddHH00mmss")
                .substring(0, 16);
    }

    public static String getCurrentDateTimestamp() {
        Date date = new Date();
        return formatDate(new Timestamp(date.getTime()).toString(),
                // "yyyy-MM-dd HH:mm:ss.SSSSSSSSSS", "yyyyMMddHHmmssSS")
                //return formatDate(new Date(System.currentTimeMillis()).toString(),
                "yyyy-MM-dd HH:mm:ss.SSSSSSSSSS", DATETIME_FORMAT);
    }
    public static String getTodayStartDateTimestamp() {
        Date date = new Date();
        String dateString = formatDate(new Timestamp(date.getTime()).toString(),
                // "yyyy-MM-dd HH:mm:ss.SSSSSSSSSS", "yyyyMMddHHmmssSS")
                //return formatDate(new Date(System.currentTimeMillis()).toString(),
                "yyyy-MM-dd HH:mm:ss.SSSSSSSSSS", YYYYMMDD_);

        dateString+= " 00:00:00";
        return dateString;
    }
    public static String formatStartDateTimestamp(String startDate) {
        startDate+= " 00:00:00";
        return startDate;
    }
    public static String formatDate(String dateString){
        Date date = parseDate(dateString);
        return new SimpleDateFormat(DDMMYYYY_).format(date);
    }
    public static String formatDate(String inputDateString, String outputDateformat){
        Date date = parseDate(inputDateString);
        return new SimpleDateFormat(outputDateformat).format(date);
    }
    public static Date parseDate(String date){
        return parseDate(date, DATETIME_FORMAT);
    }

    public static Date parseDate(String date, String fmt) {
        Date d = null;
        try {
            d = new SimpleDateFormat(fmt, Locale.UK).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
    public static Date date1900YearsBefore(){
        int year = 1900;
        int month = 1;
        int day = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date date = calendar.getTime();
        return date;
    }

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String formatICDate(String dateString) {
        Date date = parseICDate(dateString);
        return new SimpleDateFormat(YYYYMMDD_).format(date);
    }

    public static Date parseICDate(String date) {
        return parseDate(date, YYYYMMDD);
    }

    // Apply decimal value based on the pattern
    public static String formatDecimal(String pattern, double s) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String stringformatoutput = myFormatter.format(s);
        return stringformatoutput;
    }

    // Retrieve cache directory
    public static String getPathToSave(Context context) {
        String rootDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            rootDir = Environment.getExternalStorageDirectory()
                    .getAbsolutePath()
                    + "/Android/data/"
                    + context.getPackageName() + "/cache";
        } else {
            File internalCacheDir = context.getCacheDir();
            // apparently on some configurations this can come back as
            // null
            if (internalCacheDir != null) {
                rootDir = internalCacheDir.getAbsolutePath();
            } else {
                //Log.d("GetPathToSave","Failed to retrieve internal cache directory");
            }
        }

        String pathToSave = rootDir + "/cachefu/image_cache/";

        return pathToSave;
    }

    // Retrieve a tiled images, for given image
    public static BitmapDrawable getTiledDrawable(Context ctx, BitmapDrawable bd) {
        BitmapDrawable TileMe = bd;
        TileMe.setTileModeX(Shader.TileMode.REPEAT);
        TileMe.setTileModeY(Shader.TileMode.REPEAT);
        return TileMe;
    }

    // Retrieve a tiled images, for given resource id
    public static BitmapDrawable getTiledDrawable(Context ctx, int resId) {
        BitmapDrawable TileMe = new BitmapDrawable(
                BitmapFactory.decodeResource(ctx.getResources(), resId));

        return getTiledDrawable(ctx, TileMe);
    }

    // Check if a package exists, in the device
    public static boolean isPackageExists(Context ctx, String targetPackage) {
        List<ApplicationInfo> packages;
        PackageManager pm;
        pm = ctx.getPackageManager();
        packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(targetPackage))
                return true;
        }
        return false;
    }

    // Tokenize given string based on given delimiter
    public static ArrayList<String> tokenize(String data, String delimiter) {
        Log.d("data in common util",""+data);

        ArrayList<String> stringList = new ArrayList<String>();


        for (String token : data.split(delimiter, -1)) {
            // //Log.d(TAG, token);
            stringList.add(token);
        }

        Log.d("Return of string List",""+stringList);
        return stringList;
    }

    // Validate email
    public static boolean validateEmail(String email) {
        boolean isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        return isValid;
    }

    public static boolean isNumeric(String str) {
        Pattern p = Pattern.compile("^([0-9]*)$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isCurrency(String str) {
        Pattern numberPattern = Pattern.compile("^\\d{1,}(\\.\\d{0,2})?$");
        Matcher numberMatcher = numberPattern.matcher(str);
        return numberMatcher.matches();
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a)
            sb.append(String.format("%02x", b & 0xff));
        return sb.toString();
    }

    public static String hexToASCII(String hex) {
        hex = hex.replaceAll(" ", "");
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        Log.d("hexToASCII", output.toString());
        return output.toString();
    }

    public static String getHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b).toUpperCase());

            sb.append(" ");

        }
        return sb.toString();
    }

    public static int charAssign(int number) {
        if (number >= 0 && number <= 9) {
            number = number + 48;
        } else if (number + 55 == 73) {
            number = 89;
        } else if (number + 55 == 79) {
            number = 90;
        } else {
            number = number + 55;
        }
        return number;
    }
    public static int[] bytearray2intarray(byte[] barray) {
        int[] iarray = new int[barray.length];
        int i = 0;
        for (byte b : barray)
            iarray[i++] = b & 0xff;
        return iarray;
    }

    public static String generateTRNo(String t) {
        char t1 = t.charAt(3);
        char t2 = (char) charAssign(Integer.parseInt(t.substring(4, 6)));
        char t3 = (char) charAssign(Integer.parseInt(t.substring(6, 8)));
        char t4 = (char) charAssign(Integer.parseInt(t.substring(8, 10)));

        int min = Integer.parseInt(t.substring(12, 14));
        int sec = Integer.parseInt(t.substring(14, 16));
        int temp = (min * 15) + (sec / 5);
        int MS1 = temp / 34;
        int MS2 = temp % 34;
        char t5 = (char) charAssign(MS1);
        char t6 = (char) charAssign(MS2);

        return "" + t1 + t2 + t3 + t4 + t5 + t6;
    }

    public static String formatCurrency(String inputString) {
        String cleanString = inputString.replaceAll("[$,.]", "");

        BigDecimal parsed = new BigDecimal(cleanString).setScale(2,
                BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100),
                BigDecimal.ROUND_FLOOR);
        String formated = NumberFormat.getCurrencyInstance(Locale.US).format(
                parsed);

        return formated;
    }
    public static String getFormattedCardNo(String cardNumber) {
        if (cardNumber.length() == 16) {
            //Standard Package
            String formatted = cardNumber.substring(0, 4) + "-"
                    + cardNumber.substring(4, 8) + "-" + cardNumber.substring(8, 12)
                    + "-" + cardNumber.substring(12, 16);
            //Premium Package
            //String formatted = cardNumber.substring(8, 10) + "-" + cardNumber.substring(10, 14)
            //		+ "-" + cardNumber.substring(14, 16);

            return formatted;
        }

        return cardNumber;
    }

    public static Bitmap createReflectedImage(Bitmap originalImage) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // This will not scale but will flip on the Y axis
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        // Create a Bitmap with the flip matrix applied to it.
        // We only want the bottom half of the image
        Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
                (height - height / 3), width, height / 3, matrix, false);

        // Create a new Canvas with the reflected bitmap
        Canvas canvas = new Canvas(reflectionImage);

        // Draw in the reflection
        canvas.drawBitmap(reflectionImage, 0, 0, null);

        // Create a shader that is a linear gradient that covers the reflection
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, 0, 0, reflectionImage.getHeight(), 0x60ffffff, 0x00ffffff, Shader.TileMode.MIRROR);
        // Set the paint to use this shader (linear gradient)
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setAntiAlias(true);
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, 0, width, reflectionImage.getHeight(), paint);

        return reflectionImage;

    }
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        if (bitmap == null) {
            return null;
        }
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = Color.BLACK;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
    public static boolean isBase64ManualOutput(String input) {
        if(input.length()>0){
            if(input.charAt(0) == '[' && input.charAt(input.length() - 1) == ']'){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    public static String addManualBase64Format(String input) { // used to differentiate the qr code for partner app
        return "[" + input + "]";
    }
}
