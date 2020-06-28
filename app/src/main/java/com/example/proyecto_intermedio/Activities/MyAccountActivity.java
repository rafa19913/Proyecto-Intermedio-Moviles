package com.example.proyecto_intermedio.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.proyecto_intermedio.R;
import com.example.proyecto_intermedio.SampleClasses.Account;

import es.dmoral.toasty.Toasty;

public class MyAccountActivity extends AppCompatActivity {

    private TextView currentBalance, ownerName;
    private ImageView arrowBack,profilePhoto;
    private com.ornach.nobobutton.NoboButton btnEditAccount,btnEditPhoto;
    private static int LOAD_IMAGE_RESULTS = 1; // Para imagen


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);

        asignComponentsOfMyAccount();
        addInformationOfMyAccount();
        addlistenersOfMyAccount();
    }

    /**
     * Se asignan los componentes de MyAccount (botones, TextViews) de: my_account.xml
     */
    private void asignComponentsOfMyAccount(){
        profilePhoto = findViewById(R.id.ImageViewProfilePhoto);
        arrowBack = findViewById(R.id.arrowback);
        currentBalance = findViewById(R.id.TextViewCurrentBalance);
        ownerName = findViewById(R.id.TextViewOwnerName);
        btnEditAccount = findViewById(R.id.BtnEditAccount);
        btnEditPhoto = findViewById(R.id.BtnEditProfilePhoto);
        profilePhoto = findViewById(R.id.ImageViewProfilePhoto);
        arrowBack = findViewById(R.id.arrowback);
    }

    /**
     * Se agregan los listeners de mi cuenta (editar info, agregar imagen de perfil o cambiar)
     */
    private void addlistenersOfMyAccount(){
        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProfilePhoto();
            }
        });
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
        btnEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDataOfAccount();
            }
        });
        btnEditPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeProfilePhoto();
            }
        });
    }


    /**
     * TODO: Falta terminar
     */
    private void editDataOfAccount(){
        Toasty.success(this,"¡Editar datos de la cuenta!",Toast.LENGTH_SHORT,true).show();
    }


    /**
     * Se agrega la información de la cuenta: (Nombre del propietario, saldo actual, foto de perfil)
     */
    private void addInformationOfMyAccount(){
        String balance = "$"+Account.myAccount.getTotalCurrentBalanceInAccount();
        String name = Account.myAccount.getOwnerName();
        String photoPath = Account.myAccount.getProfilePhoto();

        currentBalance.setText(balance);
        ownerName.setText(name);
        profilePhoto.setImageBitmap(BitmapFactory.decodeFile(photoPath));

    }


    /**
     * Nos manda al HomeActivity.java
     */
    private void goHome(){
        onBackPressed();
    }

    /**
     * Método para detectar cuando el usuario presiona Back
     */
    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }


    // -- FALTA: terminad de acomodar bien el código para cambiar la imagen de perfil --
    private void changeProfilePhoto(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, LOAD_IMAGE_RESULTS);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
            Uri pickedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));
            profilePhoto.setImageBitmap(BitmapFactory.decodeFile(imagePath));
            Account.myAccount.setProfilePhoto(imagePath);
            Toasty.success(this, "¡Imágen actualizada correctamente!", Toast.LENGTH_SHORT, true).show();

        }

    }


}
