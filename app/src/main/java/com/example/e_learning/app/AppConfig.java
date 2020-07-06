package com.example.e_learning.app;

public class AppConfig {
	//USER
	// Server user login url
	public static String URL_LOGIN_USER = "http://192.168.1.71/e-learning/login.php";

	// Server user register url
	public static String URL_REGISTER_USER = "http://192.168.1.71/e-learning/register_user.php";

    //Lupa password
    public static final String LUPA_PASSWORD = "http://192.168.1.71/e-learning/update_password.php";
	public static final String URL_MATIKAN_NOTIF =  "http://192.168.1.71/e-learning/matikan.php";
	public static final String LAPOR_BENCANA = "http://192.168.1.71/e-learning/lapor_bencana.php";
	public static final String BERITA = "http://192.168.1.71/e-learning/tampil_berita.php";
	public static final String JML_BERITA = "http://192.168.1.71/e-learning/jml_berita.php";
	public static final String mapel = "http://192.168.1.71/e-learning/mpl.php";
	public static final String BACA =  "http://192.168.1.71/e-learning/baca.php";
	public static final String NILAI = "http://192.168.1.71/e-learning/tampil_nilai.php?nis=";
	public static final String SPP = "http://192.168.1.71/e-learning/spp.php";
	public static final String DES = "http://192.168.1.71/e-learning/des.php";

	public static final String UP = "http://192.168.1.71/e-learning/up.php";

	public static final String PDF_SITE_URL="http://192.168.1.71/e-learning/mp.php";
	public static final String CEK="http://192.168.1.71/e-learning/cek.php?nis=";
	public static final String 	Up_profil="http://192.168.1.71/e-learning/input_profil.php";
	public static final String 	Up_profil1="http://192.168.1.71/e-learning/input_profil_foto.php";
	public static final String 	Up_profil_guru="http://192.168.1.71/e-learning/input_profil_guru.php";

	public static final String 	HAPUS="http://192.168.1.71/e-learning/hp_materi.php?id=";

	public static final String PDF_SITE_URL1="http://192.168.1.71/e-learning/";
	public static final String PDF="http://192.168.1.71/e-learning/pdf.php";
	public static final String PROFIL = "http://192.168.1.71/e-learning/profil.php?nis=";
	public static final String PROFIL_GURU = "http://192.168.1.71/e-learning/profil_guru.php?id_guru=";
	public static final String nilai = "http://192.168.1.71/e-learning/data_nilai.php?id_guru=";
	public static final String FOTO = "http://192.168.1.71/e-learning/foto1.php";
	public static final String EMAIL = "http://192.168.1.71/e-learning/email.php";
	public static final String quiz = "http://192.168.1.71/e-learning/quiz.php";
	public static final String sts = "http://192.168.1.71/e-learning/sts.php";
	public static final String sts2 = "http://192.168.1.71/e-learning/sts2.php";
	public static final String siswa = "http://192.168.1.71/e-learning/siswa.php";
	public static final String EDIT_PROFIL = "http://192.168.1.71/e-learning/edit_profil.php";

	public static final String TAG_SUCCESS = "success";
	public static final String TAG_MESSAGE = "message";

	public final static String TAG_EMAIL = "email";
	public final static String TAG_ID = "id";
	public final static String TAG_PASSWORD = "password";

	//This would be the name of our shared preferences
	public static final String SHARED_PREF_NAME = "myloginapp";

	//We will use this to store the boolean in sharedpreference to track user is loggedin or not
	public static final String LOGGEDIN_SHARED_PREF = "loggedin";








	public static String STR_PUSH = "pushNotification";
	public static final String URL_REGISTER_DEVICE = "http://192.168.1.71/e-learning/notif/RegisterDevice.php";
	public static final String URL_SEND_SINGLE_PUSH = "http://192.168.1.71/e-learning/notif/sendSinglePush.php";
	public static final String URL_SEND_MULTIPLE_PUSH = "http://192.168.1.71/e-learning/notif/sendMultiplePush.php";
	public static final String URL_FETCH_DEVICES = "http://192.168.1.71/e-learning/notif/GetRegisteredDevices.php";
}
