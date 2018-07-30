package limocity.cbistech.com.limocity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;


/**
 * \brief this class is simple base class which will be extended by all activity in this application.
 * <p/>
 * and the communication with application label instance will be in this class.
 * and communication with JNI is in application class so it is an second bridge for our application.
 */
abstract public class BaseActivity extends AppCompatActivity {
    public static final int CROP_IMAGE_CODE = 300;
    private static final int MY_PERMISSIONS_REQUEST_IMAGE = 100;
    public static final int PICK_PICTURE_GALLERY = 1919;
    private static final int REQUEST_FOR_CONTACT = 1;

    private boolean isCircular;
    android.support.v7.app.ActionBar actionBar;
    private int layoutResID; //< layout id from child activity
    private View progress;
    protected ImageView imageView;
//    private PhoneBookPicker phoneBookPicker;

    protected abstract void onBackendConnected();

    protected abstract void onLayoutCreated();

//    public void showProgress() {
//        hideProgress();
//        if (progress == null) {
//            progress = View.inflate(this, R.layout.progress, null);
//            addContentView(progress
//                    , new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
//                            , ViewGroup.LayoutParams.MATCH_PARENT));
//            progress.setOnClickListener(null);
//        } else {
//            progress.setVisibility(View.VISIBLE);
//        }
//    }

//    public void hideProgress() {
//
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    View viewById = getWindow().getDecorView().findViewById(android.R.id.content);
//                    ViewGroup parent = (ViewGroup) viewById;
//                    parent.removeView(progress);
//                    progress = null;
////            progress.setVisibility(View.GONE);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected boolean shouldRegisterListeners() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return !isDestroyed() && !isFinishing();
        } else {
            return !isFinishing();
        }
    }

    public String getAppCurrentVersion() {
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        releaseVariables();
    }

    public void releaseVariables() {
        System.gc();
    }

    /**
     * function used to check internet connected
     * REMOVE THAT ONE FROM BASE
     *
     * @return
     */
//    public boolean isNetConnected() {
//        if (AppConstants.hasInternetConnection(this)) {
//            return true;
//        } else {
//            showToast("you don`t have internet connection!");
//            return false;
//        }
//    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        this.layoutResID = layoutResID;
        super.setContentView(layoutResID);
        onLayoutCreated();
    }

    public boolean checkForReadWifistatePermissionTF() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    public boolean checkForReadMessagePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(BaseActivity.this, Manifest.permission.READ_SMS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 101);
            return false;
        }
        return true;
    }

    public boolean checkForReadDevicePermissionTF() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    /*public void showAlertDialog(String title, String message, boolean showNegativeButton, final IDIalogClick idIalogClick) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog OptionDialog = builder.create();
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        idIalogClick.okClick();
                    }
                });
        if (showNegativeButton) {
            builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    OptionDialog.dismiss();
                }
            });
        }
        builder.show();
    }*/

    public boolean checkForReadDevicePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 110);
            return false;
        }
        return true;
    }


    public boolean requestForMIcPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(BaseActivity.this, Manifest.permission.RECORD_AUDIO);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 104);
            return false;
        }
        return true;
    }

    public boolean checkForReadContactPermissionTF() {
        int permissionCheck = ContextCompat.checkSelfPermission(BaseActivity.this, Manifest.permission.READ_CONTACTS);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }


    public void openPermissionScreen() {

        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }


    public void showError() {
        Toast.makeText(this, "something went wrong! please try again later", Toast.LENGTH_LONG).show();
    }

    public void showToast(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void shoWExitDialog(BaseActivity context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog OptionDialog = builder.create();
        builder.setTitle("Alert!")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        OptionDialog.dismiss();
                    }
                })
                .show();
    }

    /*public void logout() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog OptionDialog = builder.create();
        builder.setTitle("Alert!")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        BaseApplication.getDbInstance().removeFromDb();
                        OptionDialog.dismiss();
                        RemoveAllNotification();
                        startActivity(new Intent(BaseActivity.this, ActivityLogin.class));
                        finish();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        OptionDialog.dismiss();
                    }
                })
                .show();
    }*/

    public void RemoveAllNotification() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
    }


    public void goBack() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
//        if (imageView != null) {
//            if (imageView.getTag() == null) {
//                imageView.setVisibility(View.GONE);
//            }
//            return;
//        }
        switch (requestCode) {
            case PICK_PICTURE_GALLERY:

                // find the path
//                Intent intent = new Intent(this, CropImageActivity.class);
//                intent.setData(data.getData());
//                intent.putExtra("isCircular", isCircular);
//                startActivityForResult(intent, CROP_IMAGE_CODE);
//                isCircular = false;
                break;
            case CROP_IMAGE_CODE:
                imageView.setVisibility(View.VISIBLE);
                Uri selectedImage = data.getData();
                imageView.setTag(selectedImage.getEncodedPath());
                setPic(imageView, selectedImage.getEncodedPath());
                break;

            case REQUEST_FOR_CONTACT:
                Uri contactData = data.getData();
                Cursor cursor = this.getContentResolver().query(contactData, null, null, null, null);
                cursor.moveToFirst();
                String number = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                getVerfiedNumberFromPhonebook(number);
                break;

        }

    }

    /*private void getVerfiedNumberFromPhonebook(String numberFromPhonebook) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        if (numberFromPhonebook.startsWith("0")) {
            numberFromPhonebook = numberFromPhonebook.replaceAll("^0+(?=\\d+$)", "");
        }
        if (numberFromPhonebook.startsWith("+0")) {
            numberFromPhonebook = numberFromPhonebook.replace("+", "").replaceAll("^0+(?=\\d+$)", "");
        }
        if (!numberFromPhonebook.startsWith("+")) {
            numberFromPhonebook = "+" + numberFromPhonebook;
        }
        Phonenumber.PhoneNumber phoneNumber = null;
        try {
            phoneNumber = phoneNumberUtil.parse(numberFromPhonebook, null);
            boolean validNumber = phoneNumberUtil.isValidNumber(phoneNumber);
            if (validNumber) {
                int countryCode = phoneNumber.getCountryCode();
                long number = phoneNumber.getNationalNumber();
                CountryCode byCountryCode = AppConstants.getCountryByCountryCode(this, countryCode + "");
                if (byCountryCode != null) {
                    phoneBookPicker.onPhoneNumberPicked(byCountryCode, number + "");
                } else {
                    phoneBookPicker.onPhoneNumberPicked(new CountryCode(), numberFromPhonebook.replace("+", ""));
                }
            } else {
                phoneBookPicker.onPhoneNumberPicked(new CountryCode(), numberFromPhonebook.replace("+", ""));
                Log.d("pookieContact", "false:" + numberFromPhonebook);
            }
        } catch (NumberParseException e) {
            e.printStackTrace();
            Log.d("pookieContact", "false(Exec):" + numberFromPhonebook);
            phoneBookPicker.onPhoneNumberPicked(new CountryCode(), numberFromPhonebook.replace("+", ""));
        }
    }*/

    public void setPic(ImageView imageView, String path) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor << 1;

        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);

        Matrix mtx = new Matrix();
        //mtx.postRotate(90);

        try {
            ExifInterface ei = new ExifInterface(path);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    mtx.postRotate(90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    mtx.postRotate(180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    mtx.postRotate(270);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Rotating Bitmap
        Bitmap rotatedBMP =
                Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mtx, true);

        if (rotatedBMP != bitmap) {
            bitmap.recycle();
        }
        Bitmap rotatedBMP1 = Bitmap.createScaledBitmap(rotatedBMP,
                imageView.getWidth(),
                imageView.getHeight(),
                true);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bos);
        imageView.setImageBitmap(bitmap);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    public void pickGalleryImage(ImageView imageView) {
        this.imageView = imageView;
        if (checkForPermission()) {
            Intent pickGalleryImage = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if (pickGalleryImage.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(pickGalleryImage, PICK_PICTURE_GALLERY);
            } else {
                showToast("No Gallery Application found..!!");
            }
        }
    }

    /*public interface PhoneBookPicker {
        void onPhoneNumberPicked(CountryCode countryCode, String phoneNumberOnly);
    }*/



    public void pickGalleryImage(ImageView imageView, boolean isUserImage) {
        this.imageView = imageView;
        this.isCircular = isUserImage;
        if (checkForPermission()) {
            Intent pickGalleryImage = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if (pickGalleryImage.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(pickGalleryImage, PICK_PICTURE_GALLERY);
            } else {
                showToast("No Gallery Application found..!!");
            }
        }
    }

    public boolean checkForPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_IMAGE);
            return false;
        }
        return true;
    }

    public boolean checkForReadContactPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(BaseActivity.this, Manifest.permission.READ_CONTACTS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 102);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_IMAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickGalleryImage(imageView);
                } else {
                    showToast("Something went wrong, Please try later..!!");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}