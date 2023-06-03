package com.hcnetsdk.jna;

import java.util.Arrays;
import java.util.List;

import com.hikvision.netsdk.EAP_TTLS;
import com.hikvision.netsdk.NET_DVR_RECORDDAY;
import com.hikvision.netsdk.NET_DVR_RECORDSCHED;
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;

public interface HCNetSDKByJNA extends Library {

	// command begin
	public static final int NET_DVR_SET_PTZPOS  =292;
	public static final int NET_DVR_GET_PTZPOS  =293;

	public static final int NET_DVR_GET_TIMECFG = 118;
	public static final int NET_DVR_SET_TIMECFG = 119;
	//public static final int NET_DVR_GET_NTPCFG = 224; //jni
	//public static final int NET_DVR_SET_NTPCFG = 225;	//jni/**Set network application parameters(NTP)*/
	//public static final int NET_DVR_GET_DEVICECFG_V40             = 1100; //jni
	//public static final int NET_DVR_SET_DEVICECFG_V40             = 1101; //jni
	//public static final int NET_DVR_GET_NETCFG_V30	              = 1000; //jni
	//public static final int NET_DVR_SET_NETCFG_V30	              = 1001; //jni
	//public static final int NET_DVR_SET_WIFI_CFG                  = 306;  //jni
	//public static final int NET_DVR_GET_WIFI_CFG                  = 307;  //jni
	//public static final int NET_DVR_GET_AP_INFO_LIST              = 305;  //jni 
	//public static final int NET_DVR_GET_WIFI_STATUS	              = 310;  //jni
	public static final int NET_DVR_GET_RECORDCFG_V30     = 1004;
	public static final int NET_DVR_SET_RECORDCFG_V30      = 1005;
	public static final int NET_DVR_SET_ALARMIN_PARAM_V50         = 1200;
	public static final int NET_DVR_GET_ALARMIN_PARAM_V50         = 1201;
	public static final int NET_DVR_GET_WEEK_PLAN_CFG             = 2100; //get week plan of door
	public static final int NET_DVR_SET_WEEK_PLAN_CFG             = 2101; //set week plan of door
	public static final int NET_DVR_GET_ACS_WORK_STATUS           = 2123;
	public static final int NET_DVR_GET_VERIFY_WEEK_PLAN          = 2124;
	public static final int NET_DVR_SET_VERIFY_WEEK_PLAN          = 2125;
	public static final int NET_DVR_GET_CARD_RIGHT_WEEK_PLAN      = 2126;
	public static final int NET_DVR_SET_CARD_RIGHT_WEEK_PLAN      = 2127;
	public static final int NET_DVR_GET_CARD_RIGHT_WEEK_PLAN_V50  = 2304;
	public static final int NET_DVR_SET_CARD_RIGHT_WEEK_PLAN_V50  = 2305;
	public static final int NET_DVR_GET_DOOR_STATUS_HOLIDAY_PLAN  = 2102;
	public static final int NET_DVR_SET_DOOR_STATUS_HOLIDAY_PLAN  = 2103;
	public static final int NET_DVR_GET_VERIFY_HOLIDAY_PLAN       = 2128;
	public static final int NET_DVR_SET_VERIFY_HOLIDAY_PLAN       = 2129;
	public static final int NET_DVR_GET_CARD_RIGHT_HOLIDAY_PLAN   = 2130;
	public static final int NET_DVR_SET_CARD_RIGHT_HOLIDAY_PLAN   = 2131;
	public static final int NET_DVR_GET_ALARMIN_PARAM_LIST_V50   = 2237;
	public static final int NET_DVR_GET_CARD_RIGHT_HOLIDAY_PLAN_V50 = 2310;
	public static final int NET_DVR_SET_CARD_RIGHT_HOLIDAY_PLAN_V50 = 2311;
	public static final int NET_DVR_GET_DOOR_STATUS_HOLIDAY_GROUP = 2104;
	public static final int NET_DVR_SET_DOOR_STATUS_HOLIDAY_GROUP = 2105;
	public static final int NET_DVR_GET_VERIFY_HOLIDAY_GROUP      = 2132;
	public static final int NET_DVR_SET_VERIFY_HOLIDAY_GROUP      = 2133;
	public static final int NET_DVR_GET_CARD_RIGHT_HOLIDAY_GROUP  = 2134;
	public static final int NET_DVR_SET_CARD_RIGHT_HOLIDAY_GROUP  = 2135;
	public static final int NET_DVR_GET_CARD_RIGHT_HOLIDAY_GROUP_V50  = 2316;
	public static final int NET_DVR_SET_CARD_RIGHT_HOLIDAY_GROUP_V50  = 2317;
	public static final int NET_DVR_GET_DOOR_STATUS_PLAN_TEMPLATE = 2106;
	public static final int NET_DVR_SET_DOOR_STATUS_PLAN_TEMPLATE = 2107;
	public static final int NET_DVR_GET_VERIFY_PLAN_TEMPLATE      = 2136;
	public static final int NET_DVR_SET_VERIFY_PLAN_TEMPLATE      = 2137;
	public static final int NET_DVR_GET_CARD_RIGHT_PLAN_TEMPLATE  = 2138;
	public static final int NET_DVR_SET_CARD_RIGHT_PLAN_TEMPLATE  = 2139;
	public static final int NET_DVR_GET_CARD_RIGHT_PLAN_TEMPLATE_V50 = 2322;
	public static final int NET_DVR_SET_CARD_RIGHT_PLAN_TEMPLATE_V50 = 2323;
	public static final int NET_DVR_GET_DOOR_CFG                  = 2108;
	public static final int NET_DVR_SET_DOOR_CFG                  = 2109;
	public static final int NET_DVR_GET_CARD_READER_CFG_V50	      = 2505;
	public static final int NET_DVR_SET_CARD_READER_CFG_V50       = 2506;
	public static final int NET_DVR_GET_ACS_WORK_STATUS_V50	      = 2180;
	public static final int NET_DVR_SET_WIFI_WORKMODE             = 308;
	public static final int NET_DVR_GET_WIFI_WORKMODE             = 309;
	public static final int NET_DVR_CLEAR_ACS_PARAM	              = 2118;
	public static final int NET_DVR_COMPLETE_RESTORE_CTRL         = 3420;
	public static final int NET_DVR_GET_CARD_CFG = 2116;

	public static final int NET_DVR_VIDEO_CALL_SIGNAL_PROCESS = 16032;

	public static final int NET_DVR_GET_ALARMHOSTSUBSYSTEM_CFG = 2001;
	public static final int NET_DVR_SET_ALARMHOSTSUBSYSTEM_CFG = 2002;

	public static final int NET_DVR_GET_ALARMHOST_OTHER_STATUS_V50 = 2228;
	public static final int NET_DVR_GET_ALARMHOST_OTHER_STATUS_V51 = 2236;

	public static final int NET_DVR_GET_MULTI_STREAM_COMPRESSIONCFG = 3216;
	public static final int NET_DVR_SET_MULTI_STREAM_COMPRESSIONCFG = 3217;

	public static final int NET_DVR_START_GET_INPUTVOLUME = 3370;

	public static final int NET_DVR_GET_LOITERING_DETECTION = 3521;
	public static final int NET_DVR_SET_LOITERING_DETECTION = 3522;

	public static final int NET_DVR_GET_LED_AREA_INFO_LIST = 9295;

	public static final int NET_DVR_MATRIX_GETWINSTATUS = 9009;

	public static final int NET_SDK_GET_INPUTSTREAMCFG = 1551;

	public static final int NET_DVR_GET_EZVIZ_ACCESS_CFG = 3398;
	public static final int NET_DVR_SET_EZVIZ_ACCESS_CFG = 3399;


	public static final int NET_SDK_GET_VIDEOWALLDISPLAYNO  = 1553;

	public static final int  NET_SDK_GET_ALLSUBSYSTEM_BASIC_INFO =1554;

	public static final int  NET_SDK_SET_ALLSUBSYSTEM_BASIC_INFO = 1555;

	public static final int NET_SDK_GET_AUDIO_INFO =1556;

	public static final int NET_DVR_GET_VIDEOWALLDISPLAYNO  = 1732;

	public static final int NET_DVR_SET_VIDEOWALLDISPLAYPOSITION =1733;

	public static final int NET_DVR_GET_VIDEOWALLDISPLAYPOSITION =1734;

    public static final int NET_DVR_GET_CURTRIGGERMODE = 3130;

    public static final int NET_ITC_GET_RS485_ACCESSINFO = 3117;

    public static final int NET_ITC_SET_RS485_ACCESSINFO = 3118;

    public static final int NET_DVR_GET_SHOWSTRING_V30 = 1030;

    public static final int NET_DVR_SET_SHOWSTRING_V30 = 1031;

    public static final int NET_ITS_GET_OVERLAP_CFG = 5072;

    public static final int NET_ITS_SET_OVERLAP_CFG = 5073;

    public static final int NET_ITC_GET_TRIGGERCFG = 3003;

    public static final int NET_ITC_SET_TRIGGERCFG = 3004;

    public static final int NET_DVR_GET_CCDPARAMCFG_EX = 3368;
    public static final int NET_DVR_SET_CCDPARAMCFG_EX = 3369;
    
    public static final int NET_DVR_FFC_MANUAL_CTRL = 3411;

    public static final int NET_DVR_GET_THERMOMETRY_BASICPARAM_CAPABILITIES = 3620; //Get Thermometry Basic Param capabilities
    public static final int NET_DVR_GET_THERMOMETRY_BASICPARAM = 3621;
    public static final int NET_DVR_SET_THERMOMETRY_BASICPARAM = 3622;
    public static final int NET_DVR_GET_THERMOMETRY_PRESETINFO = 3624; //Get Thermometry Preset Info
    public static final int NET_DVR_SET_THERMOMETRY_PRESETINFO = 3625;

	public static final int NET_DVR_GET_FACECONTRAST_TRIGGER = 3965;
	public static final int NET_DVR_SET_FACECONTRAST_TRIGGER = 3966;

	public static final int NET_DVR_GET_FACECONTRAST_SCHEDULE = 3968;
	public static final int NET_DVR_SET_FACECONTRAST_SCHEDULE = 3969;

    public static final int NET_DVR_GET_PICCFG_V40 = 6179;
    public static final int NET_DVR_SET_PICCFG_V40 = 6180;

	public static final int NET_DVR_GET_THERMAL_PIP = 6768;
	public static final int NET_DVR_SET_THERMAL_PIP = 6769;
	
	/*************************************************New ACS ***************************************************/
	public static final int NET_DVR_GET_CARD_CFG_V50 = 2178;//Parameters to acquire new CARDS (V50)
	public static final int NET_DVR_SET_CARD_CFG_V50 = 2179;//Setting up the new parameters (V50)
	
	public static final int NET_DVR_SET_FACE_PARAM_CFG=2508;
    public static final int NET_DVR_GET_FACE_PARAM_CFG=2507;
    
    public static final int NET_DVR_GET_ACS_EVENT = 2514;
    
    public static final int NET_DVR_CAPTURE_FACE_INFO =2510;
    
    public static final int NET_DVR_DEL_FACE_PARAM_CFG = 2509;    //del face param 
    /**********************************************************************************************************/
	
	
	public static final int NET_DVR_GET_NETCFG_V50 = 1015;    //Get network parameter configuration (V50) 
	public static final int NET_DVR_SET_NETCFG_V50 = 1016;    //Set network parameter configuration (V50)
	
	public static final int NET_DVR_GET_EVENT_CARD_LINKAGE_CFG_V51 = 2518;    //Get event card number linkage configuration parameters (V51)
	public static final int NET_DVR_SET_EVENT_CARD_LINKAGE_CFG_V51 = 2519;    //Set up the event card number linkage configuration parameters (V51)

	// command end

	//alarm type
	public static final int COMM_ALARM_RULE = 0x1102;
	public static final int COMM_ALARM_PDC = 0x1103;
	public static final int COMM_UPLOAD_FACESNAP_RESULT = 0x1112;
	public static final int COMM_UPLOAD_PLATE_RESULT = 0x2800;
	public static final int COMM_SNAP_MATCH_ALARM = 0x2902;
	public static final int COMM_ITS_PLATE_RESULT = 0x3050;
	public static final int COMM_VEHICLE_CONTROL_ALARM = 0x3059;
	public static final int COMM_ALARM_V30 = 0x4000;
	public static final int COMM_ALARM_V40 = 0x4007;
	public static final int COMM_ALARM_FACE_DETECTION = 0x4010;
	public static final int COMM_ALARM_TFS = 0x1113;
	/****************************************New ACS*************************************/
	public static final int COMM_ID_INFO_ALARM=0x5200;
	/***********************************************************************************/
	
	public static final int COMM_ALARM_ACS = 0x5002;  //access card alarm
	public static final int COMM_PASSNUM_INFO_ALARM = 0x5201;  //pass number info alarm

    public static final int COMM_THERMOMETRY_ALARM = 0x5212;  
    public static final int COMM_ISAPI_ALARM = 0x6009;
	//alarm type end

	public static final int NET_DVR_DEV_ADDRESS_MAX_LEN = 129;
	public static final int NET_DVR_LOGIN_USERNAME_MAX_LEN = 64;
	public static final int NET_DVR_LOGIN_PASSWD_MAX_LEN = 64;
	public static final int NET_SDK_CALLBACK_TYPE_STATUS = 0;
	public static final int NET_SDK_CALLBACK_TYPE_PROGRESS = 1;
	public static final int NET_SDK_CALLBACK_TYPE_DATA = 2;

	public static final int NET_SDK_CALLBACK_STATUS_SUCCESS = 1000;
	public static final int NET_SDK_CALLBACK_STATUS_PROCESSING = 1001;
	public static final int NET_SDK_CALLBACK_STATUS_FAILED = 1002;
	public static final int NET_SDK_CALLBACK_STATUS_EXCEPTION = 1003;
	public static final int NET_SDK_CALLBACK_STATUS_LANGUAGE_MISMATCH = 1004;
	public static final int NET_SDK_CALLBACK_STATUS_DEV_TYPE_MISMATCH = 1005;
	public static final int NET_DVR_CALLBACK_STATUS_SEND_WAIT = 1006;

	public static final int NET_SDK_EMPLOYEE_NO_LEN = 32;
	public static final int NET_SDK_UUID_LEN = 36;
	public static final int NET_DEV_NAME_LEN = 64;
	public static final int ACS_CARD_NO_LEN = 32;
	public static final int CARD_PASSWORD_LEN = 8;
	public static final int MAX_DOOR_NUM = 32;
	public static final int MAX_CARD_RIGHT_PLAN_NUM = 4;

	public static final int STREAM_ID_LEN = 32;
	public static final int SERIALNO_LEN = 48;
	public static final int NAME_LEN = 32;
	public static final int MACADDR_LEN = 6;
	public static final int MAX_DISKNUM_V30 = 33;
	public static final int MAX_DISKNUM = 16;
	public static final int MAX_LICENSE_LEN = 16;

	public static final int MAX_HUMAN_BIRTHDATE_LEN = 10;

	public static final int MAX_CHANNUM = 16;
	public static final int MAX_ALARMIN = 16;
	public static final int MAX_ALARMOUT = 4;

	public static final int MAX_ANALOG_CHANNUM = 32;
	public static final int MAX_ANALOG_ALARMOUT = 32;
	public static final int MAX_ANALOG_ALARMIN = 32;
	public static final int MAX_IP_DEVICE = 32;
	public static final int MAX_IP_CHANNEL = 32;
	public static final int MAX_IP_ALARMIN = 128;
	public static final int MAX_IP_ALARMOUT = 64;
	public static final int ALARMHOST_DETECTOR_SERIAL_LEN_V50 = 16;

	public static final int MAX_CHANNUM_V30 = (MAX_ANALOG_CHANNUM + MAX_IP_CHANNEL);// 64
	public static final int MAX_ALARMOUT_V30 = (MAX_ANALOG_ALARMOUT + MAX_IP_ALARMOUT);// 96
	public static final int MAX_ALARMIN_V30 = (MAX_ANALOG_ALARMIN + MAX_IP_ALARMIN);// 160

	public static final int VCA_MAX_POLYGON_POINT_NUM = 10;
	public static final int MAX_REGION_NUM = 8;
	public static final int MAX_NUM_OUTPUT_CHANNEL = 512;

	public static final int MAX_DISPLAY_NUM = 512;

	public static final int ALARMHOST_MAX_SIREN_NUM = 8;
	public static final int MAX_DETECTOR_NUM = 128;
	public static final int MAX_DETECTOR_NUM_V51 = 256;
	public static final int MAX_REPEATER_NUM = 16;
	public static final int MAX_OUTPUT_MODULE_NUM = 64;
	public static final int ENUM_VCA_EVENT_TRAVERSE_PLANE      		= 1;
	public static final int ENUM_VCA_EVENT_ENTER_AREA         		= 2;
	public static final int ENUM_VCA_EVENT_EXIT_AREA           		= 3;
	public static final int ENUM_VCA_EVENT_INTRUSION      			= 4;
	public static final int ENUM_VCA_EVENT_LOITER     				= 5;
	public static final int ENUM_VCA_EVENT_LEFT_TAKE           		= 6;
	public static final int ENUM_VCA_EVENT_PARKING             		= 7;
	public static final int ENUM_VCA_EVENT_RUN            			= 8;
	public static final int ENUM_VCA_EVENT_HIGH_DENSITY        		= 9;
	public static final int ENUM_VCA_EVENT_VIOLENT_MOTION      		= 10;
	public static final int ENUM_VCA_EVENT_REACH_HIGHT         		= 11;
	public static final int ENUM_VCA_EVENT_GET_UP              		= 12;
	public static final int ENUM_VCA_EVENT_LEFT                		= 13;
	public static final int ENUM_VCA_EVENT_TAKE                		= 14;
	public static final int ENUM_VCA_EVENT_LEAVE_POSITION      		= 15;
	public static final int ENUM_VCA_EVENT_TRAIL               		= 16;
	public static final int ENUM_VCA_EVENT_KEY_PERSON_GET_UP   		= 17;
	public static final int ENUM_VCA_EVENT_STANDUP             		= 18;
	public static final int ENUM_VCA_EVENT_FALL_DOWN                = 20;
	public static final int ENUM_VCA_EVENT_AUDIO_ABNORMAL      		= 21;
	public static final int ENUM_VCA_EVENT_ADV_REACH_HEIGHT    		= 22;
	public static final int ENUM_VCA_EVENT_TOILET_TARRY        		= 23;
	public static final int ENUM_VCA_EVENT_YARD_TARRY          		= 24;
	public static final int ENUM_VCA_EVENT_ADV_TRAVERSE_PLANE  		= 25;
	public static final int ENUM_VCA_EVENT_HUMAN_ENTER         		= 29;
	public static final int ENUM_VCA_EVENT_OVER_TIME           		= 30;
	public static final int ENUM_VCA_EVENT_STICK_UP            		= 31;
	public static final int ENUM_VCA_EVENT_INSTALL_SCANNER     		= 32;
	public static final int ENUM_VCA_EVENT_PEOPLENUM_CHANGE    		= 35;
	public static final int ENUM_VCA_EVENT_SPACING_CHANGE      		= 36;
	public static final int ENUM_VCA_EVENT_COMBINED_RULE       		= 37;
	public static final int ENUM_VCA_EVENT_SIT_QUIETLY         		= 38;
	public static final int ENUM_VCA_EVENT_HIGH_DENSITY_STATUS 		= 39;


	public static final int MAX_SUBSYSTEM_ID_LEN = 16;
	public static final int ACCOUNTNUM_LEN = 6;
	public static final int ACCOUNTNUM_LEN_32 = 32;


    public static final int EZVIZ_DEVICEID_LEN = 32;
	public static final int EZVIZ_REQURL_LEN = 64;
	public static final int EZVIZ_ACCESSTOKEN_LEN = 128;
	public static final int EZVIZ_CLIENTTYPE_LEN = 32;
	public static final int EZVIZ_FEATURECODE_LEN = 64;
	public static final int EZVIZ_OSVERSION_LEN = 32;
	public static final int EZVIZ_NETTYPE_LEN = 32;
	public static final int EZVIZ_SDKVERSION_LEN = 32;
	public static final int EZVIZ_APPID_LEN = 64;

	public static final int MAX_DOMAIN_NAME = 64;
	public static final int PASSWD_LEN = 16;
	public static final int MAX_CARDNO_LEN = 48;

	public static final int NET_SDK_MAX_VERIFICATION_CODE_LEN = 32;

	public static final int NET_SDK_MAX_FDID_LEN = 256;
	public static final int MAX_UPLOADFILE_URL_LEN = 260;
	public static final int NET_SDK_MAX_PICID_LEN = 256;

	public static final int ENUM_DVR_VEHICLE_CHECK = 1;
	public static final int ENUM_MSC_SEND_DATA = 2;
	public static final int ENUM_ACS_SEND_DATA =3;
	public static final int ENUM_TME_CARD_SEND_DATA = 4;
	public static final int ENUM_TME_VEHICLE_SEND_DATA = 5;
	public static final int ENUM_DVR_DEBUG_CMD = 6;
	public static final int ENUM_DVR_SCREEN_CTRL_CMD =7;
	public static final int ENUM_CVR_PASSBACK_SEND_DATA = 8;

	public static final int ISAPI_DATA_LEN = 10*1024*1024;
	public static final int ISAPI_STATUS_LEN = 4*4096;
	public static final int BYTE_ARRAY_LEN = 1024;

	public static final int MAX_MAX_ALARMIN_NUM = 64;
	public static final int MAX_DAYS = 7;
	public static final int MAX_TIMESEGMENT_V30 = 8;
	public static final int MAX_TIMESEGMENT = 4;
	public static final int HOLIDAY_GROUP_NAME_LEN = 32;
	public static final int MAX_HOLIDAY_PLAN_NUM = 16;
	public static final int TEMPLATE_NAME_LEN = 32;
	public static final int MAX_HOLIDAY_GROUP_NUM = 16;
	public static final int DEV_TYPE_NAME_LEN = 64;
	public static final int DOOR_NAME_LEN = 32;
	public static final int STRESS_PASSWORD_LEN = 8;
	public static final int SUPER_PASSWORD_LEN = 8;
	public static final int UNLOCK_PASSWORD_LEN = 8;
	public static final int CARD_READER_DESCRIPTION = 32;
	public static final int MAX_DOOR_NUM_256 = 256;
	public static final int MAX_CASE_SENSOR_NUM = 8;
	public static final int MAX_CARD_READER_NUM = 64;
	public static final int MAX_CARD_READER_NUM_512 = 512;
	public static final int MAX_ALARMHOST_ALARMOUT_NUM = 512;
	public static final int MAX_ALARMHOST_ALARMIN_NUM = 512;
	public static final int ALARMHOST_DETECTOR_SERIAL_LEN = 9;

	public static final int PICTURE_NAME_LEN = 64;
    public static final int MAX_FACE_PIC_NUM = 30;
    public static final int CARDNUM_LEN_V30 = 40;
    public static final int MAX_ITC_LANE_NUM = 6;
    public static final int MAX_CHJC_NUM = 3;
    public static final int MAX_IOOUT_NUM = 4;
    public static final int MAX_LANEAREA_NUM = 2;
    public static final int MAX_INTERVAL_NUM = 4;
    public static final int ITC_MAX_POLYGON_POINT_NUM = 20;
    public static final int MAX_OVERLAP_ITEM_NUM = 50;
    public static final int MAX_STRINGNUM_V30 = 8;
    public static final int NET_DVR_FILE_SUCCESS = 1000;
    public static final int NET_DVR_FILE_NOFIND = 1001;
    public static final int NET_DVR_ISFINDING = 1002;
    public static final int NET_DVR_NOMOREFILE  = 1003;
    public static final int NET_DVR_FILE_EXCEPTION = 1004;
	public static final int MAX_SUBSYSTEM_NUM_V40 = 120;

    public static final int MONITORSITE_ID_LEN = 48;
    public static final int DEVICE_ID_LEN = 48;

    public static final int MAX_SHELTERNUM = 4;
    public static final int MAX_ALARMOUT_V40 = 4128;
    public static final int MAX_CHANNUM_V40 = 512;
    public static final int MAX_MULTI_AREA_NUM = 24;
    public static final int MAX_THERMOMETRY_REGION_NUM = 40;

	public static final int MAX_CATEGORY_LEN = 8;
    public static final int MAX_LICENSE_LEN_EX = 32;


    /* Commands for playing file*/
    public static final int NET_DVR_PLAYSTART  =  1;//Start play
    public static final int NET_DVR_PLAYSTOP   = 2;//Stop play
    public static final int NET_DVR_PLAYPAUSE   = 3;//Pause
    public static final int NET_DVR_PLAYRESTART = 4;//Restore
    public static final int NET_DVR_PLAYFAST  =  5;//Play Faster
    public static final int NET_DVR_PLAYSLOW   = 6;//Play Slower
    public static final int NET_DVR_PLAYNORMAL  =   7;//Normal Speed
    public static final int NET_DVR_PLAYSTARTAUDIO =  9;//Open Audio
    public static final int NET_DVR_PLAYSTOPAUDIO = 10;//Close Audio
    public static final int NET_DVR_PLAYSETPOS  =  12;//Change playing progress
    public static final int NET_DVR_RESETBUFFER	 =37  ;//reset matrix decode buffer(remote playback file)
    
    /*********************************************New ACS**********************************************/
    /*ID Card*/
    public static class NET_DVR_ID_CARD_INFO_ALARM extends Structure {
		public int dwSize;
		public NET_DVR_ID_CARD_INFO struIDCardCfg = new NET_DVR_ID_CARD_INFO();
		public int dwMajor;
		public int dwMinor;
		public NET_DVR_TIME_V30 struSwipeTime = new NET_DVR_TIME_V30();
		public byte byNetUser[] = new byte[16];
		public NET_DVR_IPADDR struRemoteHostAddr = new NET_DVR_IPADDR();
		public int dwCardReaderNo;
		public int dwDoorNo;
		public int dwPicDataLen;
		public Pointer pPicData;
		public byte byCardType;
		public byte byDeviceNo;
		public byte byMask;
		public byte byRes2;
		public int dwFingerPrintDataLen;
		public Pointer pFingerPrintData;
		public int dwCapturePicDataLen;
		public Pointer pCapturePicData;
		public int dwCertificatePicDataLen;
		public Pointer pCertificatePicData;
		public byte byCardReaderKind;
		public byte byRes3[] = new byte[2];
		public byte byIDCardInfoExtend;
		public Pointer pIDCardInfoExtend;
		public byte byRes[] = new byte[172];
		
		public NET_DVR_ID_CARD_INFO_ALARM(Pointer p){
			super(p);
	    }

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwSize", "struIDCardCfg", "dwMajor",
					"dwMinor", "struSwipeTime", "byNetUser",
					"struRemoteHostAddr", "dwCardReaderNo", "dwDoorNo",
					"dwPicDataLen", "pPicData", "byCardType", "byDeviceNo",
					"byMask", "byRes2", "dwFingerPrintDataLen", "pFingerPrintData",
					"dwCapturePicDataLen", "pCapturePicData", "dwCertificatePicDataLen", "pCertificatePicData",
					"byCardReaderKind", "byRes3", "byIDCardInfoExtend", 
					"pIDCardInfoExtend", "byRes");
		}
	}
    
    public static class NET_DVR_ID_CARD_INFO_EXTEND extends Structure
	{
		public byte byRemoteCheck; //是否需要远程核验（0-无效，1-不需要（默认），2-需要）
		public byte byThermometryUnit; //测温单位（0-摄氏度（默认），1-华氏度，2-开尔文）
		public byte byIsAbnomalTemperature; //人脸抓拍测温是否温度异常：1-是，0-否
		public byte byRes2;
		public float fCurrTemperature; //人脸温度（精确到小数点后一位）
		public NET_VCA_POINT struRegionCoordinates = new NET_VCA_POINT(); //人脸温度坐标
		public int dwQRCodeInfoLen; //二维码信息长度，不为0是表示后面带数据
		public int dwVisibleLightDataLen; //热成像相机可见光图片长度，不为0是表示后面带数据
		public int dwThermalDataLen; //热成像图片长度，不为0是表示后面带数据
		public Pointer pQRCodeInfo; //二维码信息指针
		public Pointer pVisibleLightData; //热成像相机可见光图片指针
		public Pointer pThermalData; //热成像图片指针
		public byte[]  byRes = new byte[1024];

		public NET_DVR_ID_CARD_INFO_EXTEND(Pointer p){
			super(p);
		}

		@Override
		protected List<String> getFieldOrder(){
			return Arrays.asList("byRemoteCheck","byThermometryUnit","byIsAbnomalTemperature","byRes2",
					"fCurrTemperature","struRegionCoordinates","dwQRCodeInfoLen","dwVisibleLightDataLen",
					"dwThermalDataLen","pQRCodeInfo","pVisibleLightData","pThermalData","byRes");
		}
	}
	
	public static class NET_DVR_ID_CARD_INFO extends Structure {
		public int dwSize;
		public byte byName[] = new byte[128];
		public NET_DVR_DATE struBirth = new NET_DVR_DATE();
		public byte byAddr[] = new byte[280];
		public byte byIDNum[] = new byte[32];
		public byte byIssuingAuthority[] = new byte[128];
		public NET_DVR_DATE struStartDate = new NET_DVR_DATE();
		public NET_DVR_DATE struEndDate = new NET_DVR_DATE();
		public byte byTermOfValidity;
		public byte bySex;
		public byte byNation;
		public byte byRes[] = new byte[101];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwSize", "byName", "struBirth", "byAddr",
					"byIDNum", "byIssuingAuthority", "struStartDate",
					"struEndDate", "byTermOfValidity", "bySex", "byNation",
					"byRes");
		}
	}
    
    public static class NET_DVR_FACE_PARAM_STATUS extends Structure {
    	public int dwSize;
    	public byte byCardNo[] = new byte[32];
    	public byte byCardReaderRecvStatus[] = new byte[512];
    	public byte byErrorMsg[] = new byte[32];
    	public int dwCardReaderNo;
    	public byte byTotalStatus;
    	public byte byFaceID;
    	public byte byRes[] = new byte[130];

		@Override
		protected List<String> getFieldOrder() {
    		return Arrays.asList("dwSize", "byCardNo",
    				"byCardReaderRecvStatus", "byErrorMsg", "dwCardReaderNo",
    				"byTotalStatus", "byFaceID", "byRes");
    	}
    	public NET_DVR_FACE_PARAM_STATUS(Pointer p) {
			super(p);
		}
    }

    
	
    /*门禁事件类型*/
	public static class NET_DVR_ACS_EVENT_COND extends Structure {
		public int dwSize;
		public int dwMajor;
		public int dwMinor;
		public NET_DVR_TIME struStartTime = new NET_DVR_TIME();
		public NET_DVR_TIME struEndTime = new NET_DVR_TIME();
		public byte byCardNo[] = new byte[32];
		public byte byName[] = new byte[32];
		public byte byPicEnable;
		public byte byRes2[] = new byte[3];
		public int dwBeginSerialNo;
		public int dwEndSerialNo;
		public byte byRes[] = new byte[244];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwSize", "dwMajor", "dwMinor",
					"struStartTime", "struEndTime", "byCardNo", "byName",
					"byPicEnable", "byRes2", "dwBeginSerialNo",
					"dwEndSerialNo", "byRes");
		}
	}
	
	/*人脸采集*/
	public static class NET_DVR_CAPTURE_FACE_COND extends Structure {
		public int dwSize;
		public byte byRes[] = new byte[128];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwSize", "byRes");
		}
	}
	
	
	/*人脸参数*/
	public static class NET_DVR_FACE_PARAM_COND extends Structure {
		public int dwSize;
		public byte byCardNo[] = new byte[32];
		public byte byEnableCardReader[] = new byte[512];
		public int dwFaceNum;
		public byte byFaceID;
		public byte byRes[] = new byte[127];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwSize", "byCardNo", "byEnableCardReader",
					"dwFaceNum", "byFaceID", "byRes");
		}
	}

	public static class NET_DVR_CARD_CFG_V50 extends Structure {
		public static class short_arr_1 extends Structure {
			public short byKeyInfo[] = new short[256];

			@Override
			protected List<String> getFieldOrder() {
				return Arrays.asList("byKeyInfo");
			}
		}

		public int dwSize;
		public int dwModifyParamType;
		public byte byCardNo[] = new byte[32];
		public byte byCardValid;
		public byte byCardType;
		public byte byLeaderCard;
		public byte byRes1;
		public byte byDoorRight[] = new byte[256];
		public NET_DVR_VALID_PERIOD_CFG struValid = new NET_DVR_VALID_PERIOD_CFG();
		public byte byBelongGroup[] = new byte[128];
		public byte byCardPassword[] = new byte[8];
		public short_arr_1 wCardRightPlan[] = (short_arr_1[]) new short_arr_1()
				.toArray(4);
		public int dwMaxSwipeTime;
		public int dwSwipeTime;
		public short wRoomNumber;
		public short wFloorNumber;
		public int dwEmployeeNo;
		public byte byName[] = new byte[32];
		public short wDepartmentNo;
		public short wSchedulePlanNo;
		public byte bySchedulePlanType;
		public byte byRightType;
		public byte byRes2[] = new byte[2];
		public int dwLockID;
		public byte byLockCode[] = new byte[8];
		public byte byRoomCode[] = new byte[8];
		public int dwCardRight;
		public int dwPlanTemplate;
		public int dwCardUserId;
		public byte byCardModelType;
		public byte byRes3[] = new byte[51];
		public byte bySIMNum[] = new byte[32];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwSize", "dwModifyParamType", "byCardNo",
					"byCardValid", "byCardType", "byLeaderCard", "byRes1",
					"byDoorRight", "struValid", "byBelongGroup",
					"byCardPassword", "wCardRightPlan", "dwMaxSwipeTime",
					"dwSwipeTime", "wRoomNumber", "wFloorNumber",
					"dwEmployeeNo", "byName", "wDepartmentNo",
					"wSchedulePlanNo", "bySchedulePlanType", "byRightType",
					"byRes2", "dwLockID", "byLockCode", "byRoomCode",
					"dwCardRight", "dwPlanTemplate", "dwCardUserId",
					"byCardModelType", "byRes3", "bySIMNum");
		}

		public NET_DVR_CARD_CFG_V50(Pointer p) {
			super(p);
		}
	}
	
    
	/*删除人脸*/
	public static class NET_DVR_FACE_PARAM_CTRL extends Structure {
		public int dwSize;
		public byte byMode;
		public byte byRes1[] = new byte[3];
		public NET_DVR_DEL_FACE_PARAM_MODE struProcessMode = new NET_DVR_DEL_FACE_PARAM_MODE();
		public byte byRes[] = new byte[64];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwSize", "byMode", "byRes1",
					"struProcessMode", "byRes");
		}
	}
	
	public static class NET_DVR_DEL_FACE_PARAM_MODE extends Union {
		public byte uLen[] = new byte[588];
		public NET_DVR_FACE_PARAM_BYCARD struByCard = new NET_DVR_FACE_PARAM_BYCARD();
		public NET_DVR_FACE_PARAM_BYREADER struByReader = new NET_DVR_FACE_PARAM_BYREADER();

//		protected List<String> getFieldOrder() {
//			return Arrays.asList("uLen", "struByCard", "struByReader");
//		}
	}
	
	public static class NET_DVR_FACE_PARAM_BYCARD extends Structure {
		public byte byCardNo[] = new byte[32];
		public byte byEnableCardReader[] = new byte[512];
		public byte byFaceID[] = new byte[2];
		public byte byRes1[] = new byte[42];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("byCardNo", "byEnableCardReader", "byFaceID",
					"byRes1");
		}
	}
	
	public static class NET_DVR_FACE_PARAM_BYREADER extends Structure {
		public int dwCardReaderNo;
		public byte byClearAllCard;
		public byte byRes1[] = new byte[3];
		public byte byCardNo[] = new byte[32];
		public byte byRes[] = new byte[548];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwCardReaderNo", "byClearAllCard", "byRes1",
					"byCardNo", "byRes");
		}
	}
	
    
    /*************************************************************************************************/

    public class NET_SDK_LOCAL_CFG_TYPE {
    	public static final int NET_SDK_LOCAL_CFG_TYPE_TCP_PORT_BIND = 0;
        public static final int NET_SDK_LOCAL_CFG_TYPE_UDP_PORT_BIND = 1;
        public static final int NET_SDK_LOCAL_CFG_TYPE_MEM_POOL = 2;
        public static final int NET_SDK_LOCAL_CFG_TYPE_MODULE_RECV_TIMEOUT = 3;
        public static final int NET_SDK_LOCAL_CFG_TYPE_ABILITY_PARSE = 4;
        public static final int NET_SDK_LOCAL_CFG_TYPE_TALK_MODE = 5;
        public static final int NET_SDK_LOCAL_CFG_TYPE_PROTECT_KEY = 6;
        public static final int NET_SDK_LOCAL_CFG_TYPE_CFG_VERSION = 7;
        public static final int NET_SDK_LOCAL_CFG_TYPE_RTSP_PARAMS = 8;
        public static final int NET_SDK_LOCAL_CFG_TYPE_SIMXML_LOGIN = 9;
        public static final int NET_SDK_LOCAL_CFG_TYPE_CHECK_DEV = 10;
        public static final int NET_SDK_LOCAL_CFG_TYPE_SECURITY = 11;
        public static final int NET_SDK_LOCAL_CFG_TYPE_EZVIZLIB_PATH = 12;
        public static final int NET_SDK_LOCAL_CFG_TYPE_CHAR_ENCODE = 13;
        public static final int NET_SDK_LOCAL_CFG_TYPE_PROXYS = 14;
        public static final int NET_DVR_LOCAL_CFG_TYPE_LOG = 15;
        public static final int NET_DVR_LOCAL_CFG_TYPE_STREAM_CALLBACK = 16;
        public static final int NET_DVR_LOCAL_CFG_TYPE_GENERAL = 17;
        public static final int NET_DVR_LOCAL_CFG_TYPE_PTZ = 18;
    }

	public static class NET_DVR_TIME_V30 extends Structure{
		public short	wYear;
		public byte		byMonth;
		public byte		byDay;
		public byte		byHour;
		public byte		byMinute;
		public byte		bySecond;
		public byte		byRes;
		public short    wMilliSec;
		public byte   cTimeDifferenceH;     
		public byte   cTimeDifferenceM;
		@Override 
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("wYear", "byMonth", "byDay", "byHour","byMinute", "bySecond","byRes", "wMilliSec", "cTimeDifferenceH","cTimeDifferenceM");
		}
	}

	public static class NET_DVR_IPADDR extends Structure {
		public byte[] sIpV4 = new byte[16];
		public byte[] byRes = new byte[128];

		public String toString() {
			return "NET_DVR_IPADDR.sIpV4: " + new String(sIpV4) + "\n"
					+ "NET_DVR_IPADDR.byRes: " + new String(byRes) + "\n";
		}
		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("sIpV4", "byRes");
		}
	}

	public static class NET_VCA_POINT extends Structure{
		 public  float  fX;
		 public float  fY;
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("fX", "fY");
		}
	}

	public static class NET_VCA_LINE extends Structure{
		 public NET_VCA_POINT   struStart = new NET_VCA_POINT();
		 public NET_VCA_POINT   struEnd = new NET_VCA_POINT();
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struStart", "struEnd");
		}
	}

	public static class NET_VCA_POLYGON extends Structure {

		public int dwPointNum;
		public NET_VCA_POINT[] struPos = (NET_VCA_POINT[]) new NET_VCA_POINT().toArray(10);
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwPointNum", "struPos");
		}
	}


	public static class NET_DVR_MULTI_ALARMIN_COND extends Structure {
		public int	dwSize;
		public int[] iZoneNo = new int[MAX_MAX_ALARMIN_NUM];	//zone numner start with 0,invalid < 0
		public byte[] byRes = new byte[256];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "iZoneNo","byRes");
		}
	}

	public static class NET_DVR_SINGLE_ALARMIN_PARAM extends Structure {
		public int dwSize;
		public short wZoneNo;		//readonly
		public byte	byJointSubSystem; //readonly
		public byte byType;			//type 0:real time ,1-24 hours,2-delay ,3-innter闁挎冻鎷�4-key  5-fire alarm 6-boundary 7-24 hours without sound  8-24 hours support 闁挎冻鎷�9-24 hours shake 0xff-no
		public byte[] byName = new byte[NAME_LEN];
		public short wDetectorType;	// DETECTOR_TYPE
		public short wInDelay;
		public short wOutDelay;
		public byte byAlarmType;
		public byte byZoneSignalType;
		public byte[] byDetectorSerialNo = new byte[ALARMHOST_DETECTOR_SERIAL_LEN];
		public byte byDisableDetectorTypeCfg;
		public byte[] byRes2 = new byte[118];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "wZoneNo", "byJointSubSystem", "byType",
					"byName", "wDetectorType", "wInDelay", "wOutDelay", "byAlarmType",
					"byZoneSignalType", "byDetectorSerialNo", "byDisableDetectorTypeCfg", "byRes2");
		}
	}

	public static class NET_DVR_ALARMIN_PARAM_LIST extends Structure {
		public int dwSize;
		public NET_DVR_SINGLE_ALARMIN_PARAM[] struSingleAlarmInParam = (NET_DVR_SINGLE_ALARMIN_PARAM[]) new NET_DVR_SINGLE_ALARMIN_PARAM().toArray(MAX_MAX_ALARMIN_NUM);
		public byte[] byRes = new byte[128];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "struSingleAlarmInParam", "byRes");
		}
	}

	public static class NET_DVR_SINGLE_ALARMIN_PARAM_V50 extends Structure {
		public int dwSize;
		public short wZoneNo;		//readonly
		public byte byJointSubSystem; //readonly
		public byte byType;			//type 0:real time ,1-24 hours,2-delay ,3-innter闁挎冻鎷�4-key  5-fire alarm 6-boundary 7-24 hours without sound  8-24 hours support 闁挎冻鎷�9-24 hours shake 0xff-no
		public byte[] byName = new byte[NAME_LEN];
		public short wDetectorType;	// DETECTOR_TYPE
		public short wInDelay;
		public short wOutDelay;
		public byte byAlarmType;
		public byte byZoneSignalType;
		public byte[] byDetectorSerialNo = new byte[ALARMHOST_DETECTOR_SERIAL_LEN];
		public byte byDisableDetectorTypeCfg;
		public byte byTimeOutRange;
		public byte byDetectorSignalIntensity;
		public short wTimeOut;
		public byte byTimeOutMethod;
		public byte byAssociateFlashLamp;
		public byte byStayAwayEnabled;
		public byte bySilentModeEnabled;
		public byte[] byRes3 = new byte[2];
		public byte[] byAssociateAlarmOut = new byte[MAX_ALARMHOST_ALARMOUT_NUM];
		public byte[] byRes2 = new byte[128];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "wZoneNo", "byJointSubSystem", "byType","byName", "wDetectorType", "wInDelay", "wOutDelay", "byAlarmType", "byZoneSignalType", "byDetectorSerialNo", "byDisableDetectorTypeCfg", "byTimeOutRange", "byDetectorSignalIntensity", "wTimeOut", "byTimeOutMethod", "byAssociateFlashLamp", "byStayAwayEnabled", "bySilentModeEnabled", "byRes3", "byAssociateAlarmOut", "byRes2");
		}
	}

	public static class NET_DVR_ALARMIN_PARAM_LIST_V50 extends Structure {
		public int dwSize;
		public NET_DVR_SINGLE_ALARMIN_PARAM_V50[] struSingleAlarmInParam = (NET_DVR_SINGLE_ALARMIN_PARAM_V50[]) new NET_DVR_SINGLE_ALARMIN_PARAM_V50().toArray(MAX_MAX_ALARMIN_NUM);
		public byte[] byRes = new byte[128];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "struSingleAlarmInParam", "byRes");
		}
	}

	public static class NET_DVR_ALARMIN_PARAM_V50 extends Structure {
		public int dwSize;
		public byte[] byName = new byte[NAME_LEN];
		public short wDetectorType; //see to DETECTOR_TYPE
		public byte byType;
		public byte byUploadAlarmRecoveryReport;
		public int dwParam;
		public NET_DVR_SCHEDTIME_DAYS[] struAlarmTime = (NET_DVR_SCHEDTIME_DAYS[])new NET_DVR_SCHEDTIME_DAYS().toArray(MAX_TIMESEGMENT);
		public byte[] byAssociateAlarmOut = new byte[MAX_ALARMHOST_ALARMOUT_NUM];
		public byte[] byAssociateSirenOut = new byte[8];
		public byte bySensitivityParam;
		public byte byArrayBypass;
		public byte byJointSubSystem;
		public byte byModuleStatus;
		public short wModuleAddress;
		public byte byModuleChan;
		public byte byModuleType;
		public short wZoneIndex;
		public short wInDelay;
		public short wOutDelay;
		public byte byAlarmType;
		public byte byZoneResistor;
		public float fZoneResistorManual;
		public byte[] byDetectorSerialNo = new byte[ALARMHOST_DETECTOR_SERIAL_LEN_V50];
		public byte byZoneSignalType;
		public byte byDisableDetectorTypeCfg;
		public short wTimeOut;
		public byte[] byAssociateLampOut = new byte[8];
		public byte[] byVoiceFileName = new byte[32];
		public byte byTimeOutRange;
		public byte byDetectorSignalIntensity;
		public byte byTimeOutMethod;
		public byte byAssociateFlashLamp;
		public byte byStayAwayEnabled;
		public byte bySilentModeEnabled;
		public byte[] byRes3 = new byte[506];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "byName", "wDetectorType", "byType", "byUploadAlarmRecoveryReport", "dwParam", "struAlarmTime", "byAssociateAlarmOut", "byAssociateSirenOut", "bySensitivityParam", "byArrayBypass", "byJointSubSystem", "byModuleStatus", "wModuleAddress", "byModuleChan", "byModuleType", "wZoneIndex", "wInDelay", "wOutDelay", "byAlarmType", "byZoneResistor", "fZoneResistorManual", "byDetectorSerialNo", "byZoneSignalType", "byDisableDetectorTypeCfg", "wTimeOut", "byAssociateLampOut", "byVoiceFileName", "byTimeOutRange", "byDetectorSignalIntensity", "byTimeOutMethod", "byAssociateFlashLamp", "byStayAwayEnabled", "bySilentModeEnabled", "byRes3");
		}
	}

	public static class NET_DVR_STD_CONFIG extends Structure {
		public Pointer lpCondBuffer;
		public int dwCondSize;
		public Pointer lpInBuffer;
		public int dwInSize;
		public Pointer lpOutBuffer;
		public int dwOutSize;
		public Pointer lpStatusBuffer;
		public int dwStatusSize;
		public Pointer lpXmlBuffer;
		public int dwXmlSize;
		public byte byDataType;
		public byte[] byRes = new byte[23];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("lpCondBuffer", "dwCondSize",
					"lpInBuffer", "dwInSize", "lpOutBuffer", "dwOutSize",
					"lpStatusBuffer", "dwStatusSize", "lpXmlBuffer",
					"dwXmlSize", "byDataType", "byRes");
		}
	}

	public static class NET_DVR_STD_ABILITY extends Structure {
		public Pointer lpCondBuffer;
		public int dwCondSize;
		public Pointer lpOutBuffer;
		public int dwOutSize;
		public Pointer lpStatusBuffer;
		public int dwStatusSize;
		public int dwRetSize;
		public byte[] byRes = new byte[32];
		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("lpCondBuffer", "dwCondSize",
					"lpOutBuffer", "dwOutSize", "lpStatusBuffer",
					"dwStatusSize", "dwRetSize", "byRes");
		}
	}

	public static class NET_VCA_RECT extends Structure {
		public float fX;
		public float fY;
		public float fWidth;
		public float fHeight;
		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays
					.asList("fX", "fY", "fWidth", "fHeight");
		}
	}

	public static class NET_DVR_ALARMINFO extends Structure {
		public int dwAlarmType;
		public int dwAlarmInputNumber;
		public int[] dwAlarmOutputNumber = new int[MAX_ALARMOUT];
		public int[] dwAlarmRelateChannel = new int[MAX_CHANNUM];
		public int[] dwChannel = new int[MAX_CHANNUM];
		public int[] dwDiskNumber = new int[MAX_DISKNUM];
		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwAlarmType",
					"dwAlarmInputNumber", "dwAlarmOutputNumber",
					"dwAlarmRelateChannel", "dwChannel", "dwDiskNumber");
		}
	}

	public static class NET_DVR_TIME extends Structure {
		public int dwYear;
		public int dwMonth;
		public int dwDay;
		public int dwHour;
		public int dwMinute;
		public int dwSecond;
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwYear", "dwMonth", "dwDay",
					"dwHour", "dwMinute", "dwSecond");
		}
	}


	//public static class NET_DVR_NTPPARA extends Structure {
		//	public byte[] sNTPServer = new byte[64];           /* Domain Name or IP addr of NTP server */
		//	public short wInterval;		                       /* adjust time interval(hours) */
		//	public byte byEnableNTP;                           /* enable NTP client 0-no,1-yes*/
		//	public byte cTimeDifferenceH;                      /* UTC -12 ... +13 */
		//	public byte cTimeDifferenceM;                      /* UTC minutes 0, 30, 45*/
		//	public byte res1;
		//	public short wNtpPort;                             /* ntp server port 9000 default 123*/
		//	public byte[] res2 = new byte[8];
		//	@Override
		//	protected List<String> getFieldOrder() {
		//		// TODO Auto-generated method stub
		//		return Arrays.asList("sNTPServer", "wInterval", "byEnableNTP",
		//				"cTimeDifferenceH", "cTimeDifferenceM", "res1", "wNtpPort", "res2");
		//	}
		//}

		public static class NET_DVR_SIMPLE_DAYTIME extends Structure {
		    public byte byHour;
		    public byte byMinute;
		    public byte bySecond;
		    public byte byRes;
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("byHour", "byMinute", "bySecond",
						"byRes");
			}
		}

		public static class NET_DVR_TIME_SEGMENT extends Structure {
			public NET_DVR_SIMPLE_DAYTIME struBeginTime = new NET_DVR_SIMPLE_DAYTIME();
			public NET_DVR_SIMPLE_DAYTIME struEndTime = new NET_DVR_SIMPLE_DAYTIME();
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("struBeginTime", "struEndTime");
			}
		}

		public static class NET_DVR_SINGLE_PLAN_SEGMENT extends Structure {
		    public byte byEnable;
		    public byte byDoorStatus; //0-invalid,1-dormant,2-alawys open,3-alawys closed
		    public byte byVerifyMode; //0-invalid,1-dormant,2-card+password 3-card ,4-card or password闁挎冻鎷�5-fingerprint闁挎冻鎷�6-fingerprint and password闁挎冻鎷�7-fingerprint or card闁挎冻鎷�8-fingerprint and card闁挎冻鎷�9-fingerprint and card and password
		    public byte[] byRes = new byte[5];
		    public NET_DVR_TIME_SEGMENT struTimeSegment = new NET_DVR_TIME_SEGMENT();
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("byEnable", "byDoorStatus", "byVerifyMode", "byRes",
						"struTimeSegment");
			}
		}

		public static class NET_DVR_DATE extends Structure{
			public short wYear;
		    public byte byMonth;
		    public byte byDay;
		    @Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("wYear", "byMonth", "byDay");
			}
		}

		//2-dimen array
		public static class arrayStruPlanCfg extends Structure {
			public NET_DVR_SINGLE_PLAN_SEGMENT[] struDaysPlanCfg = (NET_DVR_SINGLE_PLAN_SEGMENT[])new NET_DVR_SINGLE_PLAN_SEGMENT().toArray(MAX_TIMESEGMENT_V30);
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("struDaysPlanCfg");
			}
		}

		public static class NET_DVR_WEEK_PLAN_CFG extends Structure {
		    public int dwSize;
		    public byte byEnable;  //0-no,1-enabled
		    public byte[] byRes1 = new byte[3];
		    public arrayStruPlanCfg[] struPlanCfg = (arrayStruPlanCfg[])new arrayStruPlanCfg().toArray(MAX_DAYS);
		    public byte[] byRes2 = new byte[16];
		    @Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "byEnable", "byRes1", "struPlanCfg",
						"byRes2");
			}
		}

		public static class NET_DVR_WEEK_PLAN_COND extends Structure {
			public int dwSize;
			public int dwWeekPlanNumber;     //no.
			public short wLocalControllerID; //in [1,64]
			public byte[] byRes = new byte[106];
		    @Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "dwWeekPlanNumber", "wLocalControllerID", "byRes");
			}
		}

		public static class NET_DVR_HOLIDAY_PLAN_CFG extends Structure {
			public int dwSize;
		    public byte byEnable; //0-no,1-enabled
		    public byte[] byRes1 = new byte[3];
		    public NET_DVR_DATE struBeginDate = new NET_DVR_DATE();
		    public NET_DVR_DATE struEndDate = new NET_DVR_DATE();
		    public NET_DVR_SINGLE_PLAN_SEGMENT[] struPlanCfg = (NET_DVR_SINGLE_PLAN_SEGMENT[])new NET_DVR_SINGLE_PLAN_SEGMENT().toArray(MAX_TIMESEGMENT_V30);
		    public byte[] byRes2 = new byte[16];
		    @Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "byEnable", "byRes1", "struBeginDate",
						"struEndDate", "struPlanCfg", "byRes2");
			}
		}

		public static class NET_DVR_HOLIDAY_PLAN_COND extends Structure {
			public int dwSize;
			public int dwHolidayPlanNumber;
			public short wLocalControllerID; //[1,64]
			public byte[] byRes = new byte[106];
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "dwHolidayPlanNumber", "wLocalControllerID", "byRes");
			}
		}

		public static class NET_DVR_HOLIDAY_GROUP_CFG extends Structure {
		    public int dwSize;
		    public byte byEnable; //0-no,1-enabled
		    public byte[] byRes1 = new byte[3];
		    public byte[] byGroupName = new byte[HOLIDAY_GROUP_NAME_LEN];
		    public int[] dwHolidayPlanNo = new int[MAX_HOLIDAY_PLAN_NUM]; //0 invalid
		    public byte[] byRes2 = new byte[32];
		    @Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "byEnable", "byRes1", "byGroupName",
						"dwHolidayPlanNo", "byRes2");
			}
		}

		public static class NET_DVR_HOLIDAY_GROUP_COND extends Structure {
			public int dwSize;
			public int dwHolidayGroupNumber;
			public short wLocalControllerID; //[1,64]
			public byte[] byRes = new byte[106];
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "dwHolidayGroupNumber", "wLocalControllerID", "byRes");
			}
		}

		public static class NET_DVR_PLAN_TEMPLATE extends Structure {
		    public int dwSize;
		    public byte byEnable;
		    public byte[] byRes1= new byte[3];
		    public byte[] byTemplateName = new byte[TEMPLATE_NAME_LEN];
		    public int dwWeekPlanNo; //闁告稏鍔忛鎼佸礆閹烘梻妞介柛娆忓殩缁憋拷0濞戞挾鍎ゅΛ銈夊极閿燂拷
		    public int[] dwHolidayGroupNo = new int[MAX_HOLIDAY_GROUP_NUM];
		    public byte[] byRes2 = new byte[32];
		    @Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "byEnable", "byRes1", "byTemplateName",
						"dwWeekPlanNo", "dwHolidayGroupNo", "byRes2");
			}
		}

		public static class NET_DVR_PLAN_TEMPLATE_COND extends Structure {
			public int dwSize;
			public int dwPlanTemplateNumber;
			public short wLocalControllerID; //[1,64]
			public byte[] byRes = new byte[106];
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "dwPlanTemplateNumber", "wLocalControllerID", "byRes");
			}
		}

		public static class NET_DVR_DEVICECFG_V40 extends Structure {
			public int dwSize;
			public byte[] sDVRName = new byte[NAME_LEN];     //DVR name
			public int dwDVRID;				//DVR ID,//V1.4(0-99), V1.5(0-255)
			public int dwRecycleRecord;		//1-yes 0-no
			//readonly
			public byte[] sSerialNumber = new byte[SERIALNO_LEN];
			public int dwSoftwareVersion;		//high 16 bit major,minor low 16 bit
			public int dwSoftwareBuildDate;		//0xYYYYMMDD
			public int dwDSPSoftwareVersion;    //DSP high 16 bit major,minor low 16 bit
			public int dwDSPSoftwareBuildDate;  //DSP 0xYYYYMMDD
			public int dwPanelVersion;		    ////high 16 bit major,minor low 16 bit
			public int dwHardwareVersion;	    ////16 MSBmajor,minor LSB 16
			public byte byAlarmInPortNum;
			public byte byAlarmOutPortNum;
			public byte byRS232Num;
			public byte byRS485Num;
			public byte byNetworkPortNum;
			public byte byDiskCtrlNum;
			public byte byDiskNum;
			public byte byDVRType;				//DVR type, 1:DVR 2:ATM DVR 3:DVS ......
			public byte byChanNum;
			public byte byStartChan;			//DVS-1,DVR - 1
			public byte byDecordChans;
			public byte byVGANum;				//VGA
			public byte byUSBNum;				//USB
		        public byte byAuxoutNum;
		        public byte byAudioNum;
			public byte byIPChanNum;			//low 8,high 8 see byHighIPChanNum
			public byte byZeroChanNum;
		        public byte bySupport;              //0-not support,1-support
		        //bySupport & 0x1, intelligent search
		        //bySupport & 0x2, backup
		        //bySupport & 0x4, compressinfo config
		        //bySupport & 0x8, multi netcard
		        //bySupport & 0x10, SADP
		        //bySupport & 0x20, Raid card
		        //bySupport & 0x40, IPSAN search
			//bySupport & 0x80, rtp over rtsp
			public byte byEsataUseage;		//Esata ,0-backup,1-record
		        public byte byIPCPlug;			//0-close ,1-open
			public byte byStorageMode;		//0-HDD group,1-quota, 2-frame extract
			public byte bySupport1;		 //0-not support,1-support
			//bySupport1 & 0x1, snmp v30
			//bySupport1 & 0x2, playback or download
			//bySupport1 & 0x4, alarm priority
			//bySupport1 & 0x8, set alarm time extend
			//bySupport1 & 0x10, multi HDD(over 33)
			//bySupport1 & 0x20, rtsp over http
			public short wDevType;
			public byte[] byDevTypeName = new byte[DEV_TYPE_NAME_LEN];
			public byte bySupport2;
			//bySupport2 & 0x1, OSD extra
			public byte byAnalogAlarmInPortNum;
			public byte byStartAlarmInNo;
		    public byte byStartAlarmOutNo;
		    public byte byStartIPAlarmInNo;
		    public byte byStartIPAlarmOutNo;
		    public byte byHighIPChanNum;
			public byte[] byRes2= new byte[9];			//reserved
		    @Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "sDVRName", "dwDVRID", "dwRecycleRecord",
						"sSerialNumber", "dwSoftwareVersion", "dwSoftwareBuildDate", "dwDSPSoftwareVersion",
						"dwDSPSoftwareBuildDate", "dwPanelVersion", "dwHardwareVersion", "byAlarmInPortNum",
						"byAlarmOutPortNum", "byRS232Num", "byRS485Num", "byNetworkPortNum", "byDiskCtrlNum",
						"byDiskNum", "byDVRType", "byChanNum", "byStartChan", "byDecordChans", "byVGANum",
						"byUSBNum", "byAuxoutNum", "byAudioNum", "byIPChanNum", "byZeroChanNum", "bySupport",
						"byEsataUseage", "byIPCPlug", "byStorageMode", "bySupport1", "wDevType", "byDevTypeName",
						"bySupport2", "byAnalogAlarmInPortNum", "byStartAlarmInNo", "byStartAlarmOutNo",
						"byStartIPAlarmInNo", "byStartIPAlarmOutNo", "byHighIPChanNum", "byRes2");
			}
		}

		public static class NET_DVR_DOOR_CFG extends Structure {
		    public int dwSize;
		    public byte[] byDoorName = new byte[DOOR_NAME_LEN];
		    public byte byMagneticType;
		    public byte byOpenButtonType;
		    public byte byOpenDuration;
		    public byte byDisabledOpenDuration;
		    public byte byMagneticAlarmTimeout;
		    public byte byEnableDoorLock;
		    public byte byEnableLeaderCard;
		    public byte byLeaderCardMode;
		    public int dwLeaderCardOpenDuration;
		    public byte[] byStressPassword = new byte[STRESS_PASSWORD_LEN];
		    public byte[] bySuperPassword = new byte[SUPER_PASSWORD_LEN];
		    public byte[] byUnlockPassword = new byte[UNLOCK_PASSWORD_LEN];
		    public byte byUseLocalController;
		    public byte byRes1;
		    public short wLocalControllerID;
		    public short wLocalControllerDoorNumber;
		    public short wLocalControllerStatus;
		    public byte byLockInputCheck;
		    public byte byLockInputType;
		    public byte byDoorTerminalMode;
		    public byte byOpenButton;
		    public byte byLadderControlDelayTime;
		    public byte[] byRes2 = new byte[43];
		    @Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "byDoorName", "byMagneticType", "byOpenButtonType",
						"byOpenDuration", "byDisabledOpenDuration", "byMagneticAlarmTimeout", "byEnableDoorLock",
						"byEnableLeaderCard", "byLeaderCardMode", "dwLeaderCardOpenDuration", "byStressPassword",
						"bySuperPassword", "byUnlockPassword", "byUseLocalController", "byRes1", "wLocalControllerID",
						"wLocalControllerDoorNumber", "wLocalControllerStatus", "byLockInputCheck", "byLockInputType",
						"byDoorTerminalMode", "byOpenButton", "byLadderControlDelayTime", "byRes2");
			}
		}

		public static class NET_DVR_CARD_READER_CFG_V50 extends Structure {
		    public int dwSize;
		    public byte byEnable;
		    public byte byCardReaderType; //,1-DS-K110XM/MK/C/CK,2-DS-K192AM/AMP,3-DS-K192BM/BMP闁挎冻鎷�4-DS-K182AM/AMP闁挎冻鎷�5-DS-K182BM/BMP闁挎冻鎷�6-DS-K182AMF/ACF闁挎冻鎷�7-闂傚闄勯悧鎾箣閿燂拷485濞戞挸绉村﹢顏嗙棯閸栵紕绀�8- DS-K1101M/MK闁挎冻鎷�9- DS-K1101C/CK闁挎冻鎷�10- DS-K1102M/MK/M-A闁挎冻鎷�11- DS-K1102C/CK闁挎冻鎷�12- DS-K1103M/MK闁挎冻鎷�13- DS-K1103C/CK闁挎冻鎷�14- DS-K1104M/MK闁挎冻鎷�15- DS-K1104C/CK闁挎冻鎷�16- DS-K1102S/SK/S-A闁挎冻鎷�17- DS-K1102G/GK闁挎冻鎷�18- DS-K1100S-B闁挎冻鎷�19- DS-K1102EM/EMK闁挎冻鎷�20- DS-K1102E/EK闁挎冻鎷�21- DS-K1200EF闁挎冻鎷�22- DS-K1200MF闁挎冻鎷�23- DS-K1200CF闁挎冻鎷�24- DS-K1300EF闁挎冻鎷�25- DS-K1300MF闁挎冻鎷�26- DS-K1300CF闁挎冻鎷�27- DS-K1105E闁挎冻鎷�28- DS-K1105M闁挎冻鎷�29- DS-K1105C闁挎冻鎷�30- DS-K182AMF闁挎冻鎷�31- DS-K196AMF闁挎冻鎷�32-DS-K194AMP闁挎冻鎷�33-DS-K1T200EF/EF-C/MF/MF-C/CF/CF-C,34-DS-K1T300EF/EF-C/MF/MF-C/CF/CF-C闁挎冻鎷�35-DS-K1T105E/E-C/M/M-C/C/C-C,36-DS-K1T803F/F-M/F-S/F-E,37-DS-K1A801F/F-M/F-S/F-E,38-DS-K1107M/MK,39-DS-K1107E/EK,40-DS-K1107S/SK,41-DS-K1108M/MK,42-DS-K1108E/EK,43-DS-K1108S/SK,44-DS-K1200F,45-DS-K1S110-I,46-DS-K1T200M-PG/PGC,47-DS-K1T200M-PZ/PZC,48-DS-K1109H
		    public byte byOkLedPolarity;
		    public byte byErrorLedPolarity;
		    public byte byBuzzerPolarity;
		    public byte bySwipeInterval;
		    public byte byPressTimeout;
		    public byte byEnableFailAlarm;
			public byte byMaxReadCardFailNum;
			public byte byEnableTamperCheck;
			public byte byOfflineCheckTime;
			public byte byFingerPrintCheckLevel;
			public byte byUseLocalController;
			public byte byRes1;
			public short wLocalControllerID;
			public short wLocalControllerReaderID;
			public short wCardReaderChannel;
			public byte byFingerPrintImageQuality;
			public byte byFingerPrintContrastTimeOut;
			public byte byFingerPrintRecogizeInterval;
			public byte byFingerPrintMatchFastMode;
			public byte byFingerPrintModuleSensitive;
			public byte byFingerPrintModuleLightCondition;
			public byte byFaceMatchThresholdN;
			public byte byFaceQuality;
			public byte byFaceRecogizeTimeOut;
			public byte byFaceRecogizeInterval;
			public short wCardReaderFunction;
			public byte[] byCardReaderDescription = new byte[CARD_READER_DESCRIPTION];
			public short wFaceImageSensitometry;
			public byte byLivingBodyDetect;
			public byte byFaceMatchThreshold1;
			public byte[] byRes = new byte[256];
		    @Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "byEnable", "byCardReaderType", "byOkLedPolarity",
						"byErrorLedPolarity", "byBuzzerPolarity", "bySwipeInterval", "byPressTimeout",
						"byEnableFailAlarm", "byMaxReadCardFailNum", "byEnableTamperCheck", "byOfflineCheckTime",
						"byFingerPrintCheckLevel", "byUseLocalController", "byRes1", "wLocalControllerID", "wLocalControllerReaderID",
						"wCardReaderChannel", "byFingerPrintImageQuality", "byFingerPrintContrastTimeOut", "byFingerPrintRecogizeInterval", "byFingerPrintMatchFastMode",
						"byFingerPrintModuleSensitive", "byFingerPrintModuleLightCondition", "byFaceMatchThresholdN",
						"byFaceQuality", "byFaceRecogizeTimeOut", "byFaceRecogizeInterval", "wCardReaderFunction",
						"byCardReaderDescription", "wFaceImageSensitometry", "byLivingBodyDetect", "byFaceMatchThreshold1", "byRes");
			}
		}

			public static class NET_DVR_ACS_WORK_STATUS extends Structure
		{
			public int dwSize;
			public byte[]	byDoorLockStatus = new byte[MAX_DOOR_NUM];
			public byte[]	byDoorStatus = new byte[MAX_DOOR_NUM];
			public byte[]	byMagneticStatus = new byte[MAX_DOOR_NUM];
			public byte[]	byCaseStatus = new byte[MAX_CASE_SENSOR_NUM];
			public short wBatteryVoltage;
			public byte byBatteryLowVoltage;
			public byte byPowerSupplyStatus;
			public byte byMultiDoorInterlockStatus;
			public byte byAntiSneakStatus;
			public byte byHostAntiDismantleStatus;
			public byte byIndicatorLightStatus;
			public byte[]	byCardReaderOnlineStatus = new byte[MAX_CARD_READER_NUM];
			public byte[]	byCardReaderAntiDismantleStatus = new byte[MAX_CARD_READER_NUM];
			public byte[]	byCardReaderVerifyMode = new byte[MAX_CARD_READER_NUM];
			public byte[]	bySetupAlarmStatus = new byte[MAX_ALARMHOST_ALARMIN_NUM];
			public byte[]	byAlarmInStatus = new byte[MAX_ALARMHOST_ALARMIN_NUM];
			public byte[]	byAlarmOutStatus = new byte[MAX_ALARMHOST_ALARMOUT_NUM];
			public int dwCardNum;
			public byte[]	byRes2 = new byte[32];
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize","byDoorLockStatus","byDoorStatus","byMagneticStatus","byCaseStatus","wBatteryVoltage","byBatteryLowVoltage",
						"byPowerSupplyStatus","byMultiDoorInterlockStatus","byAntiSneakStatus","byHostAntiDismantleStatus","byIndicatorLightStatus",
						"byCardReaderOnlineStatus","byCardReaderAntiDismantleStatus","byCardReaderVerifyMode","bySetupAlarmStatus","byAlarmInStatus",
						"byAlarmOutStatus","dwCardNum","byRes2");
			}
		}

		public static class NET_DVR_ACS_WORK_STATUS_V50 extends Structure {
			public int dwSize;
			public byte[] byDoorLockStatus = new byte[MAX_DOOR_NUM_256];
			public byte[] byDoorStatus = new byte[MAX_DOOR_NUM_256];
			public byte[] byMagneticStatus = new byte[MAX_DOOR_NUM_256];
			public byte[] byCaseStatus = new byte[MAX_CASE_SENSOR_NUM];
			public short wBatteryVoltage;
			public byte byBatteryLowVoltage;
			public byte byPowerSupplyStatus;
			public byte byMultiDoorInterlockStatus;
			public byte byAntiSneakStatus;
			public byte byHostAntiDismantleStatus;
			public byte byIndicatorLightStatus;
			public byte[] byCardReaderOnlineStatus = new byte[MAX_CARD_READER_NUM_512];
			public byte[] byCardReaderAntiDismantleStatus = new byte[MAX_CARD_READER_NUM_512];
			public byte[] byCardReaderVerifyMode = new byte[MAX_CARD_READER_NUM_512];
			public byte[] bySetupAlarmStatus = new byte[MAX_ALARMHOST_ALARMIN_NUM];
			public byte[] byAlarmInStatus = new byte[MAX_ALARMHOST_ALARMIN_NUM];
			public byte[] byAlarmOutStatus = new byte[MAX_ALARMHOST_ALARMOUT_NUM];
			public int dwCardNum;
			public byte byFireAlarmStatus;
			public byte[] byRes2 = new byte[123];
		    @Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "byDoorLockStatus", "byDoorStatus", "byMagneticStatus",
						"byCaseStatus", "wBatteryVoltage", "byBatteryLowVoltage", "byPowerSupplyStatus", "byMultiDoorInterlockStatus",
						"byAntiSneakStatus", "byHostAntiDismantleStatus", "byIndicatorLightStatus", "byCardReaderOnlineStatus",
						"byCardReaderAntiDismantleStatus", "byCardReaderVerifyMode", "bySetupAlarmStatus", "byAlarmInStatus", "byAlarmOutStatus",
						"dwCardNum", "byFireAlarmStatus", "byRes2");
			}
		}

		public static class NET_DVR_ACS_PARAM_TYPE extends Structure {
		    public int dwSize;
		    public int dwParamType;
		    public byte[] byRes = new byte[32];
		    @Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "dwParamType", "byRes");
			}
		}

		public static class NET_DVR_WIFI_WORKMODE extends Structure {
			public int   dwSize;
			public int   dwNetworkInterfaceMode;
			@Override
			protected List<String> getFieldOrder() {
						// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "dwNetworkInterfaceMode");
			}
		}

		public static class NET_DVR_COMPLETE_RESTORE_INFO extends Structure {
			public int   dwSize ;
			public int   dwChannel;
			public byte[]    byRes = new byte[64];
			@Override
			protected List<String> getFieldOrder() {
						// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "dwChannel", "byRes");
			}
		}

	public static class NET_DVR_XML_CONFIG_INPUT extends Structure
	{
		public int 		dwSize;
		public Pointer	lpRequestUrl;
		public int		dwRequestUrlLen;
		public Pointer	lpInBuffer;
		public int		dwInBufferSize;
		public int		dwRecvTimeOut;
		public byte[]	byRes = new byte[32];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "lpRequestUrl", "dwRequestUrlLen", "lpInBuffer", "dwInBufferSize",
					"dwRecvTimeOut", "byRes");
		}
	}

	public static class NET_DVR_XML_CONFIG_OUTPUT extends Structure {
		public int dwSize;
		public Pointer lpOutBuffer;
		public int dwOutBufferSize;
		public int dwReturnedXMLSize;
		public Pointer lpStatusBuffer;
		public int dwStatusSize;
		public byte[] byRes = new byte[32];
		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "lpOutBuffer",
					"dwOutBufferSize", "dwReturnedXMLSize", "lpStatusBuffer",
					"dwStatusSize", "byRes");
		}
	}

	public static class NET_DVR_CALL_ROOM_CFG extends Structure {
		public int dwSize;
		public short nFloorNumber;
		public short wRoomNumber;
		public byte byManageCenter;
		public byte[] byRes = new byte[127];
		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "nFloorNumber", "wRoomNumber", "byManageCenter", "byRes");
		}
	}

	public static class NET_DVR_ALARMER extends Structure {
		public byte byUserIDValid;
		public byte bySerialValid;
		public byte byVersionValid;
		public byte byDeviceNameValid;
		public byte byMacAddrValid;
		public byte byLinkPortValid;
		public byte byDeviceIPValid;
		public byte bySocketIPValid;
		public int lUserID;
		public byte[] sSerialNumber = new byte[SERIALNO_LEN];
		public int dwDeviceVersion;
		public byte[] sDeviceName = new byte[NAME_LEN];
		public byte[] byMacAddr = new byte[MACADDR_LEN];
		public short wLinkPort;
		public byte[] sDeviceIP = new byte[128];
		public byte[] sSocketIP = new byte[128];
		public byte byIpProtocol;
		public byte[] byRes2 = new byte[11];
		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("byUserIDValid",
					"bySerialValid", "byVersionValid", "byDeviceNameValid",
					"byMacAddrValid", "byLinkPortValid", "byDeviceIPValid",
					"bySocketIPValid", "lUserID", "sSerialNumber",
					"dwDeviceVersion", "sDeviceName", "byMacAddr", "wLinkPort",
					"sDeviceIP", "sSocketIP", "byIpProtocol", "byRes2");
		}
	}

	public static class NET_DVR_ALARMINFO_V30 extends Structure {
		public int dwAlarmType;
		public int dwAlarmInputNumber;
		public byte[] byAlarmOutputNumber = new byte[MAX_ALARMOUT_V30];
		public byte[] byAlarmRelateChannel = new byte[MAX_CHANNUM_V30];
		public byte[] byChannel = new byte[MAX_CHANNUM_V30];
		public byte[] byDiskNumber = new byte[MAX_DISKNUM_V30];

		public NET_DVR_ALARMINFO_V30(Pointer p) {
			super(p);
		}
		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwAlarmType",
					"dwAlarmInputNumber", "byAlarmOutputNumber",
					"byAlarmRelateChannel", "byChannel", "byDiskNumber");
		}
	}

	public static class struIOAlarm extends Structure{
		public int dwAlarmInputNo;
		public int dwTrigerAlarmOutNum;
		public int dwTrigerRecordChanNum;
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwAlarmInputNo","dwTrigerAlarmOutNum", "dwTrigerRecordChanNum");
		}
	}

	public static class NET_DVR_TIME_EX extends Structure{
		public short wYear;
		public byte byMonth;
		public byte byDay;
		public byte byHour;
		public byte byMinute;
		public byte bySecond;
		public byte byRes;
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("wYear","byMonth", "byDay", "byHour","byMinute", "bySecond", "byRes");
		}
	}

	public static class NET_VCA_DEV_INFO extends Structure{
		public NET_DVR_IPADDR struDevIP = new NET_DVR_IPADDR();
		public short wPort;
		public byte byChannel;
		public byte byIvmsChannel;
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struDevIP", "wPort", "byChannel", "byIvmsChannel");
		}
	}

	public static class  struRecordingHost extends Structure{
		public byte bySubAlarmType;
		public byte[] byRes1 = new byte[3];
		public NET_DVR_TIME_EX struRecordEndTime = new NET_DVR_TIME_EX();
		public byte[] byRes = new byte[116];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("bySubAlarmType", "byRes1", "struRecordEndTime", "byRes");
		}
	}

	public static class struAlarmHardDisk extends Structure{
		public int dwAlarmHardDiskNum;

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwAlarmHardDiskNum");
		}
	}

	public static class struAlarmChannel extends Structure{
		public int dwAlarmChanNum;

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwAlarmChanNum");
		}
	}

	public static class uStruAlarm extends Union
	{
			public byte[] byUnionLen = new byte[128];
			public struIOAlarm struioAlarm = new struIOAlarm();
			public struAlarmHardDisk strualarmHardDisk = new struAlarmHardDisk();
			public struAlarmChannel sstrualarmChannel = new struAlarmChannel();
			public struRecordingHost strurecordingHost = new struRecordingHost();
	}

	public static class NET_DVR_ALRAM_FIXED_HEADER extends Structure{
		 public int                       dwAlarmType;
		 public NET_DVR_TIME_EX             struAlarmTime = new NET_DVR_TIME_EX();
		 public uStruAlarm ustruAlarm = new uStruAlarm();
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwAlarmType", "struAlarmTime", "ustruAlarm");
		}
	}

	public static class NET_DVR_ALARMINFO_V40 extends Structure {
		public NET_DVR_ALRAM_FIXED_HEADER struAlarmFixedHeader = new NET_DVR_ALRAM_FIXED_HEADER();
		public Pointer pAlarmData;
		public NET_DVR_ALARMINFO_V40(Pointer p){
			super(p);
		}
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struAlarmFixedHeader", "pAlarmData");
		}
	}

	public static class NET_DVR_PLATE_INFO extends Structure{
		public byte byPlateType;
		public byte byColor;
		public byte byBright;
		public byte byLicenseLen;
		public byte byEntireBelieve;
		public byte byRegion;
		public byte byCountry;
		public byte[] byRes = new byte[33];
		public NET_VCA_RECT struPlateRect = new NET_VCA_RECT();
		public byte[] sLicense = new byte[MAX_LICENSE_LEN];
		public byte[] byBelieve = new byte[MAX_LICENSE_LEN];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("byPlateType", "byColor", "byBright", "byLicenseLen", "byEntireBelieve", "byRegion",
					"byCountry", "byRes", "struPlateRect", "sLicense", "byBelieve");
		}

	}

    public static class NET_DVR_VEHICLE_INFO extends Structure{
		public int dwIndex;
		public byte byVehicleType;
		public byte byColorDepth;
		public byte byColor;
		public byte byRadarState;
		public short wSpeed;
		public short wLength;
		public byte byIllegalType;
		public byte byVehicleLogoRecog;
		public byte byVehicleSubLogoRecog;
		public byte byVehicleModel;
		public byte[] byCustomInfo = new byte[16];
		public short wVehicleLogoRecog;
		public byte  byIsParking;
        public byte  byRes;
        public int dwParkingTime; //Parking time, unit: s
        public byte  byBelieve; //byIllegalType believe,1-100
        public byte  byCurrentWorkerNumber;//Current Operating Number
        public byte  byCurrentGoodsLoadingRate;//Current cargo loading rate 0-empty 1-less 2-medium 3-more 4-full
        public byte  byDoorsStatus;//Door status 0 - Door closure 1 - Door opening
		public byte[] byRes3 = new byte[4];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwIndex", "byVehicleType", "byColorDepth", "byColor", "byRadarState", "wSpeed",
					"wLength", "byIllegalType", "byVehicleLogoRecog", "byVehicleSubLogoRecog", "byVehicleModel",
					"byCustomInfo", "wVehicleLogoRecog", "byIsParking","byRes","dwParkingTime","byBelieve","byCurrentWorkerNumber","byCurrentGoodsLoadingRate","byDoorsStatus","byRes3");
		}
	}

	public static class NET_DVR_PLATE_RESULT extends Structure{
		public int dwSize;
		public byte byResultType;
		public byte byChanIndex;
		public short wAlarmRecordID;
		public int dwRelativeTime;
		public byte[] byAbsTime = new byte[32];
		public int dwPicLen;
		public int dwPicPlateLen;
		public int dwVideoLen;
		public byte byTrafficLight;
		public byte byPicNum;
		public byte byDriveChan;
		public byte byVehicleType;
		public int dwBinPicLen;
		public int dwCarPicLen;
		public int dwFarCarPicLen;
		public ByteByReference pBuffer3;
		public ByteByReference pBuffer4;
		public ByteByReference pBuffer5;

		public byte byRelaLaneDirectionType;
		public byte    byCarDirectionType;
		public byte[] byRes3 = new byte[6];
		public NET_DVR_PLATE_INFO struPlateInfo = new NET_DVR_PLATE_INFO();
		public NET_DVR_VEHICLE_INFO struVehicleInfo = new NET_DVR_VEHICLE_INFO();
		public ByteByReference pBuffer1;
		public ByteByReference pBuffer2;

		public NET_DVR_PLATE_RESULT(){
			super();
		}
		public NET_DVR_PLATE_RESULT(Pointer p){
			super(p);
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "byResultType", "byChanIndex", "wAlarmRecordID", "dwRelativeTime", "byAbsTime",
					"dwPicLen", "dwPicPlateLen", "dwVideoLen", "byTrafficLight", "byPicNum", "byDriveChan", "byVehicleType",
					"dwBinPicLen", "dwCarPicLen", "dwFarCarPicLen", "pBuffer3", "pBuffer4", "pBuffer5", "byRelaLaneDirectionType","byCarDirectionType",
					"byRes3", "struPlateInfo", "struVehicleInfo", "pBuffer1", "pBuffer2");
		}
	}

	public static class NET_ITS_PICTURE_INFO extends Structure{
		public int           dwDataLen;
		public byte          byType;
		public byte		     byDataType;
		public byte          byCloseUpType;
		public byte          byPicRecogMode;
		public int           dwRedLightTime;
		public byte[]        byAbsTime = new byte[32];
		public NET_VCA_RECT  struPlateRect;
		public NET_VCA_RECT  struPlateRecgRect;
		public ByteByReference      pBuffer;
		public int           dwUTCTime;
		public byte          byCompatibleAblity;
		public byte[]        byRes2 = new byte[7];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwDataLen", "byType", "byDataType", "byCloseUpType", "byPicRecogMode", "dwRedLightTime",
					"byAbsTime", "struPlateRect", "struPlateRecgRect", "pBuffer", "dwUTCTime", "byCompatibleAblity", "byRes2");
		}
	}

	public static class NET_ITS_PLATE_RESULT extends Structure{
		public int				   dwSize;
		public int      		   dwMatchNo;
		public byte				   byGroupNum;
		public byte                byPicNo;
		public byte                bySecondCam;
		public byte                byFeaturePicNo;
		public byte                byDriveChan;
		public byte                byVehicleType;
		public byte                byDetSceneID;
		public byte                byVehicleAttribute;
		public short               wIllegalType;
		public byte[]              byIllegalSubType = new byte[8];
		public byte                byPostPicNo;
		public byte                byChanIndex;
		public short               wSpeedLimit;
		public byte[]              byRes2 = new byte[2];
		public NET_DVR_PLATE_INFO  struPlateInfo = new NET_DVR_PLATE_INFO();
		public NET_DVR_VEHICLE_INFO     struVehicleInfo = new NET_DVR_VEHICLE_INFO();
		public byte[]              byMonitoringSiteID = new byte[48];
		public byte[]              byDeviceID = new byte[48];
		public byte                byDir;
		public byte                byDetectType;
		public byte                byRelaLaneDirectionType;
		public byte                byCarDirectionType;
		public int                 dwCustomIllegalType;
		public ByteByReference     pIllegalInfoBuf;
		public byte                byIllegalFromatType;
		public byte                byPendant;
		public byte                byDataAnalysis;
		public byte                byYellowLabelCar;
		public byte                byDangerousVehicles;
		public byte                byPilotSafebelt;
		public byte                byCopilotSafebelt;
		public byte                byPilotSunVisor;
		public byte                byCopilotSunVisor;
		public byte                byPilotCall;
		public byte                byBarrierGateCtrlType;
		public byte                byAlarmDataType;
		public NET_DVR_TIME_V30    struSnapFirstPicTime = new NET_DVR_TIME_V30();
		public int                 dwIllegalTime;
		public int                 dwPicNum;
		public NET_ITS_PICTURE_INFO[]     struPicInfo = (NET_ITS_PICTURE_INFO[])new NET_ITS_PICTURE_INFO().toArray(6);

		public NET_ITS_PLATE_RESULT(Pointer p){
			super(p);
		}
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "dwMatchNo", "byGroupNum", "byPicNo", "bySecondCam", "byFeaturePicNo","byDriveChan",
					"byVehicleType", "byDetSceneID", "byVehicleAttribute", "wIllegalType", "byIllegalSubType", "byPostPicNo",
					"byChanIndex", "wSpeedLimit", "byRes2", "struPlateInfo", "struVehicleInfo", "byMonitoringSiteID", "byDeviceID",
					"byDir", "byDetectType", "byRelaLaneDirectionType", "byCarDirectionType", "dwCustomIllegalType",
					"pIllegalInfoBuf","byIllegalFromatType", "byPendant", "byDataAnalysis", "byYellowLabelCar", "byDangerousVehicles", "byPilotSafebelt",
					"byCopilotSafebelt", "byPilotSunVisor", "byCopilotSunVisor", "byPilotCall", "byBarrierGateCtrlType", "byAlarmDataType",
					"struSnapFirstPicTime", "dwIllegalTime", "dwPicNum", "struPicInfo");
		}
	}

	public static class NET_VCA_TARGET_INFO extends Structure {
		public int          dwID;
		public NET_VCA_RECT struRect = new NET_VCA_RECT();
		public byte[]       byRes = new byte[4];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwID", "struRect", "byRes");
		}

	}

	public static class NET_VCA_TRAVERSE_PLANE extends Structure {
		public NET_VCA_LINE    struPlaneBottom = new NET_VCA_LINE();
		public int             dwCrossDirection;
		public byte            bySensitivity;
		public byte            byPlaneHeight;
		public byte            byDetectionTarget;
		public byte[]          byRes2 = new byte[37];

		public NET_VCA_TRAVERSE_PLANE(Pointer pointer){
			super(pointer);
		}
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struPlaneBottom", "dwCrossDirection", "bySensitivity", "byPlaneHeight", "byDetectionTarget", "byRes2");
		}
	}

	public static class NET_VCA_AREA extends Structure {
		public NET_VCA_POLYGON    struRegion = new NET_VCA_POLYGON();
		public byte               byDetectionTarget;
		public byte[]             byRes = new byte[7];

		public NET_VCA_AREA(Pointer pointer) {
			// TODO Auto-generated constructor stub
			super(pointer);
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "byDetectionTarget", "byRes");
		}
	}

	public static class NET_VCA_INTRUSION extends Structure {
		public NET_VCA_POLYGON    struRegion = new NET_VCA_POLYGON();
		public short              wDuration;
		public byte               bySensitivity;
		public byte               byRate;
		public byte               byDetectionTarget;
		public byte[]             byRes = new byte[3];

		public NET_VCA_INTRUSION(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "bySensitivity", "byRate", "byDetectionTarget", "byRes");
		}
	}

	public static class NET_VCA_LOITER extends Structure {
		public NET_VCA_POLYGON    struRegion = new NET_VCA_POLYGON();
		public short              wDuration;
		public byte[]             byRes = new byte[6];


		public NET_VCA_LOITER(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}


		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "byRes");
		}
	}

	public static class NET_VCA_TAKE_LEFT extends Structure {
		public NET_VCA_POLYGON    struRegion = new NET_VCA_POLYGON();
		public short              wDuration;
		public byte[]             byRes = new byte[6];


		public NET_VCA_TAKE_LEFT(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}


		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "byRes");
		}
	}

	public static class NET_VCA_PARKING extends Structure {
		public NET_VCA_POLYGON    struRegion = new NET_VCA_POLYGON();
		public short              wDuration;
		public byte[]             byRes = new byte[6];

		public NET_VCA_PARKING(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "byRes");
		}
	}

	public static class NET_VCA_RUN extends Structure {
		public NET_VCA_POLYGON    struRegion = new NET_VCA_POLYGON();
		public float              fRunDistance;
		public byte               bySensitivity;
		public byte               byMode;
		public byte               byDetectionTarget;
		public byte               byRes;

		public NET_VCA_RUN(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "fRunDistance", "bySensitivity", "byMode", "byDetectionTarget", "byRes");
		}
	}

	public static class NET_VCA_HIGH_DENSITY extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public float             fDensity;
		public byte              bySensitivity;
		public byte              byRes;
		public short             wDuration;

		public NET_VCA_HIGH_DENSITY(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "fDensity", "bySensitivity", "byRes", "wDuration");
		}
	}

	public static class NET_VCA_VIOLENT_MOTION extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short             wDuration;
		public byte              bySensitivity;
		public byte              byMode;
		public byte[]            byRes = new byte[4];

		public NET_VCA_VIOLENT_MOTION(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "bySensitivity", "byMode", "byRes");
		}
	}

	public static class NET_VCA_REACH_HIGHT extends Structure {
		public NET_VCA_LINE     struVcaLine = new NET_VCA_LINE();
		public short            wDuration;
		public byte[]           byRes = new byte[6];

		public NET_VCA_REACH_HIGHT(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struVcaLine", "wDuration", "byRes");
		}
	}

	public static class NET_VCA_GET_UP extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short             wDuration;
		public byte              byMode;
		public byte              bySensitivity;
		public byte[]            byRes = new byte[4];

		public NET_VCA_GET_UP(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "byMode", "bySensitivity", "byRes");
		}
	}

	public static class NET_VCA_LEFT extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short             wDuration;
		public byte              bySensitivity;
		public byte[]            byRes = new byte[5];

		public NET_VCA_LEFT(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "bySensitivity", "byRes");
		}
	}

	public static class NET_VCA_TAKE extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short             wDuration;
		public byte              bySensitivity;
		public byte[]            byRes = new byte[5];

		public NET_VCA_TAKE(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "bySensitivity", "byRes");
		}
	}

	public static class NET_VCA_HUMAN_ENTER extends Structure {
		public int[]            dwRes = new int[23];

		public NET_VCA_HUMAN_ENTER(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwRes");
		}
	}

	public static class NET_VCA_OVER_TIME extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short             wDuration;
		public byte[]            byRes = new byte[6];

		public NET_VCA_OVER_TIME(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "byRes");
		}
	}

	public static class NET_VCA_STICK_UP extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short             wDuration;
		public byte				 bySensitivity;
		public byte[]            byRes = new byte[5];

		public NET_VCA_STICK_UP(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "bySensitivity", "byRes");
		}
	}

	public static class NET_VCA_SCANNER extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short             wDuration;
		public byte				 bySensitivity;
		public byte[]            byRes = new byte[5];

		public NET_VCA_SCANNER(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "bySensitivity", "byRes");
		}
	}

	public static class NET_VCA_LEAVE_POSITION extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short             wLeaveDelay;
		public short             wStaticDelay;
		public byte              byMode;
		public byte              byPersonType;
		public byte[]            byRes = new byte[2];

		public NET_VCA_LEAVE_POSITION(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wLeaveDelay", "wStaticDelay", "byMode", "byPersonType", "byRes");
		}
	}

	public static class NET_VCA_TRAIL extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short             wRes;
		public byte				 bySensitivity;
		public byte[]            byRes = new byte[5];

		public NET_VCA_TRAIL(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wRes", "bySensitivity", "byRes");
		}
	}

	public static class NET_VCA_FALL_DOWN extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short             wDuration;
		public byte				 bySensitivity;
		public byte				 byHeightThreshold;
		public byte[]            byRes = new byte[4];

		public NET_VCA_FALL_DOWN(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDuration", "bySensitivity", "byHeightThreshold", "byRes");
		}
	}

	public static class NET_VCA_AUDIO_ABNORMAL extends Structure {
		public short    wDecibel;
		public byte     bySensitivity;
		public byte     byAudioMode;
		public byte     byEnable;
		public byte     byThreshold;
		public byte[]   byRes = new byte[54];

		public NET_VCA_AUDIO_ABNORMAL(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("wDecibel", "bySensitivity", "byAudioMode", "byEnable", "byThreshold", "byRes");
		}
	}

	public static class NET_VCA_ADV_REACH_HEIGHT extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public int				 dwCrossDirection;
		public byte[]            byRes = new byte[4];

		public NET_VCA_ADV_REACH_HEIGHT(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "dwCrossDirection", "byRes");
		}
	}

	public static class NET_VCA_TOILET_TARRY extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short			 wDelay;
		public byte[]            byRes = new byte[6];

		public NET_VCA_TOILET_TARRY(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDelay", "byRes");
		}
	}

	public static class NET_VCA_YARD_TARRY extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public short			 wDelay;
		public byte[]            byRes = new byte[6];

		public NET_VCA_YARD_TARRY(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "wDelay", "byRes");
		}
	}

	public static class NET_VCA_ADV_TRAVERSE_PLANE extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public int               dwCrossDirection;
		public byte              bySensitivity;
		public byte[]            byRes = new byte[3];

		public NET_VCA_ADV_TRAVERSE_PLANE(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "dwCrossDirection", "bySensitivity", "byRes");
		}
	}

	public static class NET_VCA_STANDUP extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public byte              bySensitivity;
		public byte              byHeightThreshold;
		public short             wDuration;
		public byte[]            byRes = new byte[4];

		public NET_VCA_STANDUP(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "bySensitivity", "byHeightThreshold", "wDuration", "byRes");
		}
	}

	public static class NET_VCA_PEOPLENUM_CHANGE extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public byte              bySensitivity;
		public byte              byPeopleNumThreshold;
		public byte              byDetectMode;
		public byte              byNoneStateEffective;
		public short             wDuration;
		public byte[]            byRes = new byte[2];

		public NET_VCA_PEOPLENUM_CHANGE(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "bySensitivity", "byPeopleNumThreshold", "byDetectMode", "byNoneStateEffective", "wDuration", "byRes");
		}
	}

	public static class NET_VCA_SPACING_CHANGE extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public float             fSpacingThreshold;
		public byte              bySensitivity;
		public byte              byDetectMode;
		public short             wDuration;

		public NET_VCA_SPACING_CHANGE(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "fSpacingThreshold", "bySensitivity", "byDetectMode", "wDuration");
		}
	}

	public static class NET_VCA_RELATE_RULE_PARAM extends Structure {
		public byte    byRuleID;
		public byte    byRes;
		public short   wEventType;
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("byRuleID", "byRes", "wEventType");
		}
	}

	public static class NET_VCA_COMBINED_RULE extends Structure {
		public byte                     byRuleSequence;
		public byte[]                   byRes = new byte[7];
		public int                      dwMinInterval;
		public int                      dwMaxInterval;
		public NET_VCA_RELATE_RULE_PARAM     struRule1Raram = new NET_VCA_RELATE_RULE_PARAM();
		public NET_VCA_RELATE_RULE_PARAM     struRule2Raram = new NET_VCA_RELATE_RULE_PARAM();
		public byte[]                   byRes1 = new byte[36];

		public NET_VCA_COMBINED_RULE(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("byRuleSequence", "byRes", "dwMinInterval", "dwMaxInterval", "struRule1Raram", "struRule2Raram", "byRes1");
		}
	}

	public static class NET_VCA_SIT_QUIETLY extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public int               dwDuration;
		public byte[]			 byRes = new byte[4];

		public NET_VCA_SIT_QUIETLY(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "dwDuration", "byRes");
		}
	}

	public static class NET_VCA_HIGH_DENSITY_STATUS extends Structure {
		public NET_VCA_POLYGON   struRegion = new NET_VCA_POLYGON();
		public float             fDensity;
		public byte              bySensitivity;
		public byte[]            byRes = new byte[3];

		public NET_VCA_HIGH_DENSITY_STATUS(Pointer p) {
			super(p);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "fDensity", "bySensitivity", "byRes");
		}
	}

	public static class NET_VCA_EVENT_UNION extends Union
	{
		public int[] uLen = new int[23];
		//not list all the event structure,it's too slow,see COMM_ALARM_RULE in JNATest
	}

	public static class NET_VCA_RULE_INFO extends Structure {
		public byte             byRuleID;
		public byte             byRes;
		public short            wEventTypeEx;
		public byte[]           byRuleName = new byte[NAME_LEN];
		public int              dwEventType;
		public NET_VCA_EVENT_UNION   uEventParam = new NET_VCA_EVENT_UNION();
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("byRuleID", "byRes", "wEventTypeEx", "byRuleName", "dwEventType", "uEventParam");
		}
	}

	public static class NET_VCA_RULE_ALARM extends Structure {
		public int              dwSize;
		public int              dwRelativeTime;
		public int              dwAbsTime;
		public NET_VCA_RULE_INFO     struRuleInfo = new NET_VCA_RULE_INFO();
		public NET_VCA_TARGET_INFO   struTargetInfo = new NET_VCA_TARGET_INFO();
		public NET_VCA_DEV_INFO      struDevInfo = new NET_VCA_DEV_INFO();
		public int              dwPicDataLen;
		public byte             byPicType;
		public byte             byRelAlarmPicNum;
		public byte             bySmart;
		public byte             byRes;
		public int              dwAlarmID;
		public byte[]           byRes2 = new byte[8];
		public ByteByReference  pImage;

		public NET_VCA_RULE_ALARM(Pointer p){
			super(p);
		}
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "dwRelativeTime", "dwAbsTime", "struRuleInfo", "struTargetInfo", "struDevInfo",
					"dwPicDataLen", "byPicType", "byRelAlarmPicNum", "bySmart", "byRes", "dwAlarmID", "byRes2", "pImage");
		}
	}

	public static class NET_DVR_STREAM_INFO extends Structure {
		public int dwSize;
		public byte[] byID = new byte[STREAM_ID_LEN];
		public int dwChannel;
		public byte[] byRes = new byte[32];

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "byID", "dwChannel",
					"byRes");
		}
	}

	public static class NET_DVR_MULTI_STREAM_COMPRESSIONCFG_COND extends
			Structure {
		public int dwSize;
		public NET_DVR_STREAM_INFO struStreamInfo = new NET_DVR_STREAM_INFO();
		public int dwStreamType;
		public byte byRes[] = new byte[32];

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "struStreamInfo",
					"dwStreamType", "byRes");
		}
	}

	public static class NET_DVR_COMPRESSION_INFO_V30 extends Structure {
		public byte byStreamType;
		public byte byResolution;
		public byte byBitrateType;
		public byte byPicQuality;
		public int dwVideoBitrate;
		public int dwVideoFrameRate;
		public short wIntervalFrameI;
		public byte byIntervalBPFrame;
		public byte byENumber;
		public byte byVideoEncType;
		public byte byAudioEncType;
		public byte[] byres = new byte[10];

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("byStreamType", "byResolution",
					"byBitrateType", "byPicQuality", "dwVideoBitrate",
					"dwVideoFrameRate", "wIntervalFrameI", "byIntervalBPFrame",
					"byENumber", "byVideoEncType", "byAudioEncType", "byres");
		}
	}

	public static class NET_DVR_MULTI_STREAM_COMPRESSIONCFG extends Structure {
		public int dwSize;
		public int dwStreamType;
		public NET_DVR_COMPRESSION_INFO_V30 struStreamPara = new NET_DVR_COMPRESSION_INFO_V30();
		public byte[] byRes = new byte[80];

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "dwStreamType",
					"struStreamPara", "byRes");
		}
	}

	public static class INT_ARRAY extends Structure {
		public int[] iValue;

		public INT_ARRAY(int iLen) {
			iValue = new int[iLen];
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("iValue");
		}
	}

	public static class BYTE_ARRAY extends Structure {
		public byte[] byValue;

		public BYTE_ARRAY(int iLen) {
			byValue = new byte[iLen];
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("byValue");
		}
	}



	public static class NET_DVR_LOITERING_REGION extends Structure {
		public NET_VCA_POLYGON struRegion = new NET_VCA_POLYGON();
		public byte bySensitivity;
		public byte byTimeThreshold;
		public byte[] byRes = new byte[62];

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struRegion", "bySensitivity",
					"byTimeThreshold", "byRes");
		}
	}

	public static class NET_DVR_LOITERING_DETECTION extends Structure {
		public int dwSize;
		public byte byEnabled;
		public byte[] byRes1 = new byte[3];
		public NET_DVR_LOITERING_REGION[] struRegion = (NET_DVR_LOITERING_REGION[]) new NET_DVR_LOITERING_REGION()
				.toArray(MAX_REGION_NUM);
		public byte[] byRes2 = new byte[128];

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "byEnabled",
					"byRes1", "struRegion", "byRes2");
		}
	}

	public static class NET_DVR_SMART_REGION_COND extends Structure {
		public int dwSize;
		public int dwChannel;
		public int dwRegion;

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "dwChannel",
					"dwRegion");
		}
	}

	public static class NET_DVR_INPUTVOLUME extends Structure {
		public int dwSize;
		public byte byAudioInputChan;
		public byte[] byRes = new byte[63];

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "byAudioInputChan",
					"byRes");
		}
	}

	public static class NET_DVR_CARD_CFG_COND extends Structure {
		public int dwSize;
		public int dwCardNum;
		public byte byCheckCardNo;
		public byte[] byRes1 = new byte[3];
		public short wLocalControllerID;
		public byte[]  byRes2 = new byte[2];
		public int dwLockID;
		public byte[]  byRes3 = new byte[20];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "dwCardNum", "byCheckCardNo", "byRes1",
					"wLocalControllerID", "byRes2", "dwLockID", "byRes3");
		}
	}

	public static class NET_DVR_VALID_PERIOD_CFG extends Structure {
		public byte byEnable;
		public byte[] byRes1 = new byte[3];
		public NET_DVR_TIME_EX struBeginTime = new NET_DVR_TIME_EX();
		public NET_DVR_TIME_EX struEndTime = new NET_DVR_TIME_EX();
		public byte[] byRes2 = new byte[32];

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("byEnable", "byRes1",
					"struBeginTime", "struEndTime", "byRes2");
		}
	}

	public static class arrayCardRightPlan extends Structure {
		public byte[] byDoorRightPlan = new byte[MAX_CARD_RIGHT_PLAN_NUM];

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("byDoorRightPlan");
		}
	}

	public static class NET_DVR_CARD_CFG extends Structure {
		public int dwSize;
		public int dwModifyParamType;
		public byte[] byCardNo = new byte[ACS_CARD_NO_LEN];
		public byte byCardValid;
		public byte byCardType;
		public byte byLeaderCard;
		public byte byRes1;
		public int dwDoorRight;
		public NET_DVR_VALID_PERIOD_CFG struValid = new NET_DVR_VALID_PERIOD_CFG();
		public int dwBelongGroup;
		public byte[] byCardPassword = new byte[CARD_PASSWORD_LEN];
		public arrayCardRightPlan[] byCardRightPlan = new arrayCardRightPlan[MAX_DOOR_NUM];
		public int dwMaxSwipeTime;
		public int dwSwipeTime;
		public short wRoomNumber;
		public short wFloorNumber;
		public byte[] byRes2 = new byte[20];

		public NET_DVR_CARD_CFG(Pointer p) {
			super(p);
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "dwModifyParamType",
					"byCardNo", "byCardValid", "byCardType", "byLeaderCard",
					"byRes1", "dwDoorRight", "struValid", "dwBelongGroup",
					"byCardPassword", "byCardRightPlan", "dwMaxSwipeTime",
					"dwSwipeTime", "wRoomNumber", "wFloorNumber", "byRes2");
		}
	}

	public static class NET_DVR_LED_AREA_COND extends Structure
	{
	    public int dwSize;
	    public int dwVideoWallNo;
	    public int dwLEDAreaNo;
	    public byte[] byRes = new byte[32];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize","dwVideoWallNo",
					"dwLEDAreaNo","byRes");
		}
	}

	public static class NET_DVR_RECTCFG_EX extends Structure
	{
		public int  dwXCoordinate;
		public int  dwYCoordinate;
		public int  dwWidth;
		public int  dwHeight;
		public byte [] byRes= new byte[4];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwXCoordinate","dwYCoordinate",
					"dwWidth","dwHeight","byRes");
		}
	}

	public static class NET_DVR_LED_AREA_INFO extends Structure
	{
		public int dwSize;
		public int dwLEDAreaNo;
	    public NET_DVR_RECTCFG_EX struRect = new NET_DVR_RECTCFG_EX();
	    public int []dwaOutputNo= new int[MAX_NUM_OUTPUT_CHANNEL];
	    public byte byAreaType;
	    public byte [] byRes= new byte[31];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize","dwLEDAreaNo","struRect",
					"dwaOutputNo","byAreaType", "byRes");
		}
	}

	public static class NET_DVR_LED_AREA_INFO_LIST extends Structure
	{
		public int dwSize;
		public int dwLEDAreaNum;
	    public Pointer lpstruBuffer;
	    public int dwBufferSize;
	    public byte [] byRes= new byte[32];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize","dwLEDAreaNum","lpstruBuffer",
					"dwBufferSize","byRes");
		}
	}

	public static class NET_DVR_MATRIX_PASSIVEMODE extends Structure
	{
		public short	wTransProtol;
		public short	wPassivePort;
		public NET_DVR_IPADDR	struMcastIP = new NET_DVR_IPADDR();
		public byte	    byStreamType;
		public byte[]	byRes= new byte[7];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("wTransProtol","wPassivePort",
				"struMcastIP","byStreamType","byRes");
		}
	}

	public static class NET_DVR_ALARMHOST_OTHER_STATUS_V50 extends Structure
	{
		public int dwSize;
		public byte[]	bySirenStatus = new byte[ALARMHOST_MAX_SIREN_NUM];
		public byte[]	byDetetorPower = new byte[MAX_DETECTOR_NUM];
		public byte[]	byDetetorConnection = new byte[MAX_DETECTOR_NUM];
		public byte[]	byRes= new byte[1024];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize","bySirenStatus","byDetetorPower","byDetetorConnection","byRes");
		}
	}

	public static class NET_DVR_ALARMHOST_OTHER_STATUS_V51 extends Structure
	{
		public int dwSize;
		public byte[] bySirenStatus = new byte[ALARMHOST_MAX_SIREN_NUM];
		public byte[] byDetetorPower = new byte[MAX_DETECTOR_NUM_V51];
		public byte[] byDetetorConnection = new byte[MAX_DETECTOR_NUM_V51];
		public byte[] bySirenPower = new byte[ALARMHOST_MAX_SIREN_NUM];
		public byte[] bySirenTamperStatus = new byte[ALARMHOST_MAX_SIREN_NUM];
		public byte[] byPowerStausEnabled = new byte[MAX_DETECTOR_NUM_V51 / 8];
		public byte[] byDetetorPowerStatus = new byte[MAX_DETECTOR_NUM_V51 / 8];
		public byte byDetetorPowerType;
		public byte[] byRes2 = new byte[3];
		public byte[] byRepeaterStatus = new byte[MAX_REPEATER_NUM];
		public byte[] byRepeaterTamperStatus = new byte[MAX_REPEATER_NUM / 8];
		public byte[] byAlarmOutTamperStatus = new byte[MAX_ALARMHOST_ALARMOUT_NUM / 8];
		public byte[] byOutputModuleTamperStatus = new byte[MAX_OUTPUT_MODULE_NUM / 8];
		public byte[] byRes = new byte[338];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "bySirenStatus", "byDetetorPower", "byDetetorConnection", "bySirenPower", "bySirenTamperStatus", "byPowerStausEnabled", "byDetetorPowerStatus", "byDetetorPowerType", "byRes2", "byRepeaterStatus", "byRepeaterTamperStatus", "byAlarmOutTamperStatus", "byOutputModuleTamperStatus", "byRes");
		}
	}

	public static class NET_DVR_NOAMAL_SUB_SYSTEM extends Structure
	{
		public int 		dwBeJoinedSubSystem;
		public byte[]	byRes= new byte[16];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwBeJoinedSubSystem","byRes");
		}
	}

	public static class NET_DVR_PUBLIC_SUB_SYSTEM extends Structure
	{
		public int 		dwJointSubSystem;
		public byte[]	byRes= new byte[16];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwJointSubSystem","byRes");
		}
	}

	public static class NET_DVR_JOINT_SUB_SYSTEM extends Union
	{
		public NET_DVR_NOAMAL_SUB_SYSTEM	struNormalSubSystem = new NET_DVR_NOAMAL_SUB_SYSTEM();
		public NET_DVR_PUBLIC_SUB_SYSTEM	struPublicSubSystem = new NET_DVR_PUBLIC_SUB_SYSTEM();
		public byte[]	byRes= new byte[20];
//		@Override
//		protected List<String> getFieldOrder() {
//			// TODO Auto-generated method stub
//			return Arrays.asList("struNormalSubSystem","struPublicSubSystem","byRes");
//		}
	}

	public static class NET_DVR_ALARMSUBSYSTEMPARAM extends Structure
	{
		public int		dwSize;
		public short	wEnterDelay;
		public short	wExitDelay;
		public byte	    byHostageReport;
		public byte	    bySubsystemEnable;
		public byte	    byKeyToneOfArmOrDisarm;
		public byte	    byKeyToneOfManualTestReport;
		public short	wDelayTime;
		public byte	    byEnableAlarmInDelay;
		public byte	    byPublicAttributeEnable;
		public NET_DVR_JOINT_SUB_SYSTEM	struJointSubSystem = new NET_DVR_JOINT_SUB_SYSTEM();
		public byte	    byKeyZoneArm;
		public byte	    byKeyZoneArmReport;
		public byte	    byKeyZoneDisarm;
		public byte	    byKeyZoneDisarmReport;
		public byte[]	bySubSystemID = new byte[MAX_SUBSYSTEM_ID_LEN];
		public byte	    byKeyZoneArmReportEnable;
		public byte	    byKeyZoneArmEnable;
		public byte	    byOneKeySetupAlarmEnable;
		public byte	    bySingleZoneSetupAlarmEnable;
		public byte	    byCenterType;
		public byte[]	sCenterAccount = new byte[ACCOUNTNUM_LEN];
		public byte[]	sCenterAccountV40 = new byte[ACCOUNTNUM_LEN_32];
		public byte[]	byRes2 = new byte[565];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize","wEnterDelay","wExitDelay","byHostageReport","bySubsystemEnable"
					,"byKeyToneOfArmOrDisarm","byKeyToneOfManualTestReport","wDelayTime","byEnableAlarmInDelay","byPublicAttributeEnable"
					,"struJointSubSystem","byKeyZoneArm","byKeyZoneArmReport","byKeyZoneDisarm","byKeyZoneDisarmReport"
					,"bySubSystemID","byKeyZoneArmReportEnable","byKeyZoneArmEnable","byOneKeySetupAlarmEnable","bySingleZoneSetupAlarmEnable"
					,"byCenterType","sCenterAccount","sCenterAccountV40","byRes2");
		}
	}

	public static class NET_DVR_WALLWIN_INFO extends Structure
	{
		public int 		dwSize;
		public int		dwWinNum;
		public int		dwSubWinNum;
		public int		dwWallNo;
		public byte[]	byRes = new byte[12];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize","dwWinNum","dwSubWinNum","dwWallNo","byRes");
		}
	}

	public static class NET_DVR_WALL_WIN_STATUS extends Structure
	{
		public int 		dwSize;
		public byte 	byDecodeStatus;
		public byte 	byStreamType;
		public byte 	byPacketType;
		public byte 	byFpsDecV;
		public byte 	byFpsDecA;
		public byte[] 	byRes1 = new byte[7];
		public int 		dwDecodedV;
		public int 		dwDecodedA;
		public short 	wImgW;
		public short 	wImgH;
		public byte 	byStreamMode;
		public byte[] 	byRes2 = new byte[31];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize","byDecodeStatus","byStreamType","byPacketType","byFpsDecV",
					"byFpsDecA","byRes1","dwDecodedV","dwDecodedA","wImgW",
					"wImgH","byStreamMode","byRes2");
		}
	}
	public static class NET_DVR_SETUPALARM_PARAM extends Structure
	{
		public int		dwSize;
		public byte		byLevel;
		public byte		byAlarmInfoType;
		public byte 	byRetAlarmTypeV40;
		public byte 	byRetDevInfoVersion;
		public byte 	byRetVQDAlarmType;
		public byte 	byFaceAlarmDetection;
		public byte 	bySupport;
		public byte 	byBrokenNetHttp;
		public short	wTaskNo;
		public byte[]   byRes1 = new byte[6];

		protected List<String>	getFieldOrder(){
			return Arrays.asList("dwSize", "byLevel", "byAlarmInfoType",
					"byRetAlarmTypeV40", "byRetDevInfoVersion", "byRetVQDAlarmType",
					"byFaceAlarmDetection", "bySupport", "byBrokenNetHttp", "wTaskNo", "byRes1");
		}
	}

	public static class NET_DVR_DEVICEINFO_V30 extends Structure
	{
	   public  byte[] 	sSerialNumber = new byte[SERIALNO_LEN];
	   public  byte 	byAlarmInPortNum;
	   public  byte 	byAlarmOutPortNum;
	   public  byte 	byDiskNum;
	   public  byte 	byDVRType;
	   public  byte 	byChanNum;
	   public  byte 	byStartChan;
	   public  byte 	byAudioChanNum;
	   public  byte 	byIPChanNum;
	   public  byte     byZeroChanNum;
	   public  byte     byMainProto;
	   public  byte     bySubProto;
	   public  byte     bySupport;
	   public  byte     bySupport1;
	   public  byte     bySupport2;
	   public  short    wDevType;
	   public  byte     bySupport3;
	   public  byte     byMultiStreamProto;
	   public  byte     byStartDChan;
	   public  byte     byStartDTalkChan;
	   public  byte     byHighDChanNum;
	   public  byte     bySupport4;
	   public  byte     byLanguageType;
	   public  byte     byVoiceInChanNum;
	   public  byte     byStartVoiceInChanNo;
	   public  byte[]   byRes3=new byte[2];
	   public  byte     byMirrorChanNum;
	   public  short    wStartMirrorChanNo;
	   public  byte[]   byRes2=new byte[2];

	   @Override
	   protected List<String> getFieldOrder() {
		   // TODO Auto-generated method stub
		   return Arrays.asList("sSerialNumber", "byAlarmInPortNum", "byAlarmOutPortNum", "byDiskNum",
				    "byDVRType", "byChanNum", "byStartChan", "byAudioChanNum", 
				    "byIPChanNum", "byZeroChanNum","byMainProto", "bySubProto",
				    "bySupport", "bySupport1","bySupport2", "wDevType",
				    "bySupport3", "byMultiStreamProto","byStartDChan", "byStartDTalkChan",
				    "byHighDChanNum", "bySupport4","byLanguageType", "byVoiceInChanNum",
				    "byStartVoiceInChanNo", "byRes3","byMirrorChanNum", "wStartMirrorChanNo", "byRes2");
	   }
	}

	public static class NET_DVR_DEVICEINFO_V40 extends Structure
	{
			public NET_DVR_DEVICEINFO_V30 struDeviceV30 = new NET_DVR_DEVICEINFO_V30();
			public byte bySupportLock;
			public byte byRetryLoginTime;
			public byte byPasswordLevel;
			public byte byProxyType;
			public int  dwSurplusLockTime;
			public byte byCharEncodeType;
			public byte bySupportDev5;
			public byte byLoginMode;
			public byte[] byRes2 = new byte[253];
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("struDeviceV30", "bySupportLock", "byRetryLoginTime",
						   "byPasswordLevel", "byProxyType", "dwSurplusLockTime", "byCharEncodeType",
						   "bySupportDev5", "byLoginMode", "byRes2");
			}
	}


	public static class NET_DVR_USER_LOGIN_INFO extends Structure
	{
			public byte[] sDeviceAddress = new byte[NET_DVR_DEV_ADDRESS_MAX_LEN];
			public byte byUseTransport;
			public short wPort;
			public byte[] sUserName = new byte[NET_DVR_LOGIN_USERNAME_MAX_LEN];
			public byte[] sPassword = new byte[NET_DVR_LOGIN_PASSWD_MAX_LEN];
			public FLoginResultCallBack cbLoginResult;
			public Pointer pUser;
			public boolean bUseAsynLogin;
			public byte byProxyType;
			public byte byUseUTCTime;
			public byte byLoginMode;
			public byte byHttps;
			public int iProxyID;
			public byte[] byRes3 = new byte[120];
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("sDeviceAddress", "byUseTransport", "wPort", "sUserName", "sPassword",
						"cbLoginResult", "pUser", "bUseAsynLogin", "byProxyType", 
						"byUseUTCTime", "byLoginMode", "byHttps", "iProxyID", "byRes3");
			}
	}


	public static class NET_DVR_OPEN_EZVIZ_USER_LOGIN_INFO extends Structure
	{
		public byte[] sEzvizServerAddress = new byte[NET_DVR_DEV_ADDRESS_MAX_LEN];
		public byte[] byRes1 = new byte[3];
		public short wPort;
		public byte[] byRes2 = new byte[2];
		public byte[] sUrl = new byte[EZVIZ_REQURL_LEN];
		public byte[] sAccessToken = new byte[EZVIZ_ACCESSTOKEN_LEN];
		public byte[] sDeviceID = new byte[EZVIZ_DEVICEID_LEN];
		public byte[] sClientType = new byte[EZVIZ_CLIENTTYPE_LEN];
		public byte[] sFeatureCode = new byte[EZVIZ_FEATURECODE_LEN];
		public byte[] sOsVersion = new byte[EZVIZ_OSVERSION_LEN];
		public byte[] sNetType = new byte[EZVIZ_NETTYPE_LEN];
		public byte[] sSdkVersion = new byte[EZVIZ_SDKVERSION_LEN];
		public byte[] sAppID = new byte[EZVIZ_APPID_LEN];
		public byte[] byRes3 = new byte[512];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("sEzvizServerAddress", "byRes1", "wPort", "byRes2", "sUrl",
					"sAccessToken", "sDeviceID", "sClientType", "sFeatureCode",
					"sOsVersion", "sNetType", "sSdkVersion", "sAppID", "byRes3");
		}
	}

	public static class NET_DVR_VIDEO_CALL_COND extends Structure
	{
			public int		dwSize;
			public byte[] byRes = new byte[128];
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "byRes");
			}
	}

	public static class NET_DVR_VIDEO_CALL_PARAM extends Structure
	{
			public int		dwSize;
			public int		dwCmdType;
			public byte[] byRes = new byte[128];

			public NET_DVR_VIDEO_CALL_PARAM()
			{
				super();
			}
			public NET_DVR_VIDEO_CALL_PARAM(Pointer p) {
				super(p);
			}
			@Override
			protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "dwCmdType", "byRes");
			}
	}

	public class NET_DVR_DEV_CHAN_INFO extends Structure {
		/** DVR IP address */
		public NET_DVR_IPADDR struIP = new NET_DVR_IPADDR();
		/** DVR PORT */
	    public short  wDVRPort;
	    /** Channel */
	    public byte   byChannel;
	    /** Transmit protocol:0-TCP, 1-UDP */
	    public byte	  byTransProtocol;
	    /** Stream mode: 0-main stream, 1-sub stream */
	    public byte	  byTransMode;
	    /** IPC factory type */
	    public byte	  byFactoryType;
	    /** Device type(Used by videoplatfom VCA card),
	     *  1-decoder(use decode channel No. or display channel depends on byVcaSupportChanMode in video platform ability structure),
	     *  2-coder
	     */
	    public byte	  byDeviceType;
	    /** Display channel No. used by VCA configuration */
	    public byte   byDispChan;
	    /** Display sub channel No. used by VCA configuration */
	    public byte	  bySubDispChan;
	    /** Resolution: 1-CIF 2-4CIF 3-720P 4-1080P 5-500w used by big screen controler */
	    public byte	  byResolution;
	    /** reserved */
	    public byte[] byRes = new byte[2];
	    /** Device domain name */
	    public byte[] sDomain = new byte[MAX_DOMAIN_NAME];
	    /** Remote device user name */
	    public byte[] sUserName = new byte[NAME_LEN];
	    /** Remote device password */
	    public byte[] sPassword = new byte[PASSWD_LEN];
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struIP", "wDVRPort", "byChannel", "byTransProtocol", "byTransMode",
					"byFactoryType", "byDeviceType", "byDispChan", "bySubDispChan",
					"byResolution", "byRes", "sDomain", "sUserName", "sPassword");
		}
	}


	public static class NET_DVR_SUBSYSTEM_BASIC_INFO extends Structure
	{
		public int		dwSize;
		public byte  bySubSystemType;
		public byte  bySubSystemNo ;
		public byte  [] byRes1 = new byte[2];
		public int		dwChan;
		public NET_DVR_IPADDR   struSubSystemIP;
		public NET_DVR_IPADDR   struSubSystemIPMask;
		public NET_DVR_IPADDR	 struGatewayIpAddr;
		public short wSubSystemPort;
		public byte  []byRes2 = new byte[6];
		public byte  []sSerialNumber  = new byte[SERIALNO_LEN];
		public byte  byBelongBoard;
		public byte [] byRes3 = new byte[3];
		public byte  []byDeviceName = new byte[20];
		public int		dwStartChanNo;
		public byte  byDevNo;
		public byte  [] byRes4 = new byte[63];

		public NET_DVR_SUBSYSTEM_BASIC_INFO(Pointer p) {
			super(p);
		}

		protected List<String> getFieldOrder() {
		// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "bySubSystemType", "bySubSystemNo", "byRes1", "dwChan",
					"struSubSystemIP", "struSubSystemIPMask", "struGatewayIpAddr", "wSubSystemPort", "byRes2",
					"sSerialNumber", "byBelongBoard", "byRes3", "byDeviceName", "dwStartChanNo",
					"byDevNo", "byRes4");
		}
	}

	public class NET_DVR_STREAM_MEDIA_SERVER_CFG extends Structure {
		/** Is enable */
		 public byte   byValid;
		 /** reserved1 */
		 public byte[] byRes1 = new byte[3];
		 /** stream server IP */
		 public NET_DVR_IPADDR struDevIP = new NET_DVR_IPADDR();
		 /** stream server Port */
		 public short  wDevPort;
		 /** Protocol: 0-TCP, 1-UDP */
		 public byte   byTransmitType;
		 /** reserved2 */
		 public byte[] byRes2 = new byte[69];
		 @Override
	 	 protected List<String> getFieldOrder() {
	 		 // TODO Auto-generated method stub
	 		 return Arrays.asList("byValid", "byRes1", "struDevIP", "wDevPort", "byTransmitType", "byRes2");
		 }
	}

	public class NET_DVR_PU_STREAM_CFG extends Structure {
		public int dwSize;
		public NET_DVR_STREAM_MEDIA_SERVER_CFG	struStreamMediaSvrCfg = new NET_DVR_STREAM_MEDIA_SERVER_CFG();
	    public NET_DVR_DEV_CHAN_INFO		    struDevChanInfo = new NET_DVR_DEV_CHAN_INFO();
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "struStreamMediaSvrCfg", "struDevChanInfo");
		}
	}

	public class NET_DVR_VIDEOEFFECT extends Structure {
		public byte byBrightnessLevel;  /*0- 100*/
		public byte byContrastLevel;  /*0- 100*/
		public byte bySharpnessLevel;  /*0- 100*/
		public byte bySaturationLevel;  /*0- 100*/
		public byte byHueLevel;  /*0- 100,  (Reserved) */
		public byte byEnableFunc; //enable,bit0-SMART IR,bit1-illumination,bit2-light inhibit,0-no,1-yes
		public byte byLightInhibitLevel; //light inhibit level,[1-3]
		public byte byGrayLevel; //gray level,0-[0-255],1-[16-235]
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("byBrightnessLevel", "byContrastLevel", "bySharpnessLevel", "bySaturationLevel", "byHueLevel",
					"byEnableFunc", "byLightInhibitLevel", "byGrayLevel");
		}
	}

	public static class NET_DVR_INPUTSTREAMCFG_V40 extends Structure
	{
	    public int dwSize;
		/** 0-invalid, !0-valid */
		public byte   byValid;
		/** reference to NET_DVR_CAM_MODE */
		public byte   byCamMode;
		/** input signal No. */
		public short  wInputNo;
		/** signal name */
		public byte[] sCamName = new byte[NAME_LEN];
		/** video effect parameters */
		public NET_DVR_VIDEOEFFECT 	 struVideoEffect = new NET_DVR_VIDEOEFFECT();
		/** IP signal configurations */
		public NET_DVR_PU_STREAM_CFG struPuStream = new NET_DVR_PU_STREAM_CFG();
		/** sub board No., read only */
		public short  wBoardNum;
		/** index of signal in sub board, read only */
		public short  wInputIdxOnBoard;
		/** resolution */
		public int	  dwResolution;
		/** video format, reference to VIDEO_STANDARD */
		public byte   byVideoFormat;
		/** signal status,0-invalid, 1-signal normal, 2-no signal, 3-exception  */
		public byte	  byStatus;
		/** signal group name */
		public byte[] sGroupName = new byte[NAME_LEN];
		/** relate matrix, 0-not related, 1-related,
		 *  valid when signal type is NET_DVR_CAM_BNC or NET_DVR_CAM_VGA or NET_DVR_CAM_DVI or NET_DVR_CAM_HDMI
		 */
		public byte	  byJointMatrix;
		/** joint No., read only */
		public byte	  byJointNo;
		/** color mode, 0-self define, 1-sharp, 2-normal,
		 *  3-soft,struVideoEffect is valid when color mode is self define
		 */
		public byte	  byColorMode;

		public byte	  byScreenServer;
		public byte	  byRes1[]= new byte[2];
		public int    dwInputSignalNo;

		/** reserved */
		public byte[] byRes = new byte[120];

		public NET_DVR_INPUTSTREAMCFG_V40() {

		}

		public NET_DVR_INPUTSTREAMCFG_V40(Pointer p) {
			super(p);
		}

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "byValid", "byCamMode", "wInputNo", "sCamName", "struVideoEffect",
					"struPuStream", "wBoardNum", "wInputIdxOnBoard", "dwResolution", "byVideoFormat", "byStatus",
					"sGroupName", "byJointMatrix", "byJointNo", "byColorMode", "byScreenServer", "byRes1",
					"dwInputSignalNo", "byRes");
		}
	}

	public static class NET_DVR_INPUT_SIGNAL_LIST extends Structure
	{
		public int dwSize;
		public int dwInputSignalNums;
		public Pointer pBuffer;
		public byte byRes1[] = new byte[3];
		public int dwBufLen;
		public byte byRes2[] = new byte[64];

		@Override
		protected List<String> getFieldOrder() {
		// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "dwInputSignalNums", "pBuffer", "byRes1", "dwBufLen", "byRes2");
		}
	}

	class NET_DVR_ALLSUBSYSTEMINFO_V40 extends Structure {
        public int dwSize;
        public NET_DVR_SUBSYSTEMINFO_V40[] struSubSystemInfo = new NET_DVR_SUBSYSTEMINFO_V40[MAX_SUBSYSTEM_NUM_V40];
        public byte[] byRes = new byte[8];

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "struSubSystemInfo", "byRes");
        }
    }

    class NET_DVR_SUBSYSTEMINFO_V40 extends Structure {
        public byte bySubSystemType;
        //subsystem channel number, for distributor subsystem, it means 485 serial number (just for get)
        public byte byChan;
        public byte byLoginType;//Login type, 1-direction 2-DNS, 3-peanuthull
        public byte bySlotNum;//slot number, (just for getting)
        public byte[] byRes1 = new byte[4];
        public NET_DVR_IPADDR struSubSystemIP;        /*IPaddress (can change)*/
        public NET_DVR_IPADDR struSubSystemIPMask;//subnet mask
        public NET_DVR_IPADDR struGatewayIpAddr;    /* gateway address*/
        public short wSubSystemPort;        //subsystem port (can change)
        public byte[] byRes2 = new byte[6];
        public byte[] sUserName = new byte[NAME_LEN];    /*username (just for getting)*/
        public byte[] sPassword = new byte[PASSWD_LEN];    /*password (can change)*/
        public byte[] sDomainName = new byte[MAX_DOMAIN_NAME];//domain name (can change)
        public byte[] sDnsAddress = new byte[MAX_DOMAIN_NAME];/*domain name or ip address*/
        public byte[] sSerialNumber = new byte[SERIALNO_LEN];//serial number (just for getting)
        public byte byBelongBoard;//belong board, from 1 to start, 0xff means invaild
        public byte byInterfaceType;//interface type,0-none, 1-BNC,2-VGA,3-HDMI,4-DVI,5-SDI, 6-FIBER, 7-RGB, 8-YPrPb, 9-VGA/HDMI/DVI adapter,10-3GSDI,11-VGA/DVI adapter,12-HDTVI,13-HDBaseT,14-DP,15-DVIT,16-TVI,17-VSCREEN,0xff-invaild
        public byte byInterfaceNums;//interface number,0xff means invaild
        public byte byInterfaceStartNum;//interface number to start,0xff means invaild
        public byte[] byDeviceName = new byte[20];//subsystem name
        public byte byAudioChanNums;  //audio channel number
        public byte byAudioChanStartNum; //the start of audio channel
        public byte byAudioChanType;   //audio channel type, 0-invalid, 1-audio in, 2-audio out
        public byte[] byRes3 = new byte[33];

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("bySubSystemType", "byChan", "byLoginType", "bySlotNum", "byRes1", "struSubSystemIP",
                    "struSubSystemIPMask", "struGatewayIpAddr", "wSubSystemPort", "byRes2", "sUserName", "sPassword", "sDomainName", "sDnsAddress",
                    "sSerialNumber", "byBelongBoard", "byInterfaceType", "byInterfaceNums", "byInterfaceStartNum", "byDeviceName", "byAudioChanNums", "byAudioChanStartNum",
                    "byAudioChanType", "byRes3");
        }
    }

	public static class NET_DVR_VEHICLE_CONTROL_ALARM extends Structure
	{
		public int dwSize;
		public byte  byListType;
		public byte  byPlateType;
		public byte  byPlateColor;
		public byte  byRes1;
	    public byte[] sLicense = new byte[MAX_LICENSE_LEN];
		public byte[] sCardNo = new byte[MAX_CARDNO_LEN];
		public NET_DVR_TIME_V30 struAlarmTime = new NET_DVR_TIME_V30();
	    public int dwChannel;
	    public int dwPicDataLen;
	    public byte  byPicType;
	    public byte  byRes3[] = new byte[3];
	    public ByteByReference pPicData;
	    public byte  byRes2[] = new byte[48];
	    public NET_DVR_VEHICLE_CONTROL_ALARM(Pointer p){
			super(p);
	    }
		@Override
		protected List<String> getFieldOrder() {
		// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "byListType", "byPlateType", "byPlateColor", "byRes1", "sLicense",
					"sCardNo","struAlarmTime", "dwChannel", "dwPicDataLen", "byPicType", "byRes3","pPicData","byRes2");
		}
	}

	public static class NET_VCA_HUMAN_FEATURE extends Structure
	{
		public byte byAgeGroup;
		public byte bySex;
		public byte byEyeGlass;
		public byte byAge;
		public byte byAgeDeviation;
		public byte byEthnic;
		public byte byMask;
		public byte bySmile;
		public byte byFaceExpression;
		public byte byBeard;
		public byte byRace;
		public byte byHat;
		public byte[] byRes = new byte[4];

		@Override
		protected List<String> getFieldOrder() {
		// TODO Auto-generated method stub
			return Arrays.asList("byAgeGroup", "bySex", "byEyeGlass", "byAge", "byAgeDeviation", "byEthnic", "byMask", "bySmile", "byFaceExpression", "byBeard", "byRace", "byHat", "byRes");
		}
	}

	public static class NET_DVR_FACE_DETECTION extends Structure
	{
		public int dwSize;
		public int dwRelativeTime;
		public int dwAbsTime;
		public int dwBackgroundPicLen;
		public NET_VCA_DEV_INFO struDevInfo = new NET_VCA_DEV_INFO();
		public NET_VCA_RECT[] struFacePic = (NET_VCA_RECT[])new NET_VCA_RECT().toArray(MAX_FACE_PIC_NUM);
		public byte byFacePicNum;
		public byte byRes1;
		public short wDevInfoIvmsChannelEx;
		public byte[] byRes = new byte[252];
		public ByteByReference pBackgroundPicpBuffer;
		public NET_DVR_FACE_DETECTION(Pointer p){
			super(p);
		}

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "dwRelativeTime", "dwAbsTime", "dwBackgroundPicLen", "struDevInfo", "struFacePic",
                    "byFacePicNum","byRes1", "wDevInfoIvmsChannelEx", "byRes", "pBackgroundPicpBuffer");
        }
	}

	public static class NET_PTZ_INFO extends Structure
	{
	    public float fPan;
        public float fTilt;
        public float fZoom;
        public int dwFocus;
	    public byte[] byRes = new byte[4];
		public NET_PTZ_INFO(Pointer p){
			super(p);
		}

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("fPan", "fTilt", "fZoom", "dwFocus", "byRes");
        }
	}

	public static class NET_DVR_THERMOMETRY_ALARM extends Structure
	{
        public int   dwSize;
        public int   dwChannel;
        public byte    byRuleID;
        public byte    byThermometryUnit;
        public short    wPresetNo; 
        public NET_PTZ_INFO  struPtzInfo;
        public byte    byAlarmLevel;
        public byte    byAlarmType;
        public byte    byAlarmRule;
        public byte    byRuleCalibType;
        public NET_VCA_POINT struPoint;
        public NET_VCA_POLYGON struRegion;
        public float   fRuleTemperature;
        public float   fCurrTemperature;
        public int   dwPicLen;
        public int   dwThermalPicLen;
        public int   dwThermalInfoLen;
        public ByteByReference   pPicBuff; 
        public ByteByReference   pThermalPicBuff;
        public ByteByReference   pThermalInfoBuff; 
        public NET_VCA_POINT struHighestPoint;
        public float   fToleranceTemperature;
        public int   dwAlertFilteringTime;
        public int   dwAlarmFilteringTime;
        public int   dwTemperatureSuddenChangeCycle;
        public float   fTemperatureSuddenChangeValue;
        public byte    byPicTransType;  
		public byte[] byRes = new byte[39];
		public NET_DVR_THERMOMETRY_ALARM(Pointer p){
			super(p);
		}

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "dwChannel", "byRuleID", "byThermometryUnit", "wPresetNo", "struPtzInfo", "byAlarmLevel", 
			"byAlarmType", "byAlarmRule", "byRuleCalibType", "struPoint", "struRegion", "fRuleTemperature", "fCurrTemperature", 
			"dwPicLen", "dwThermalPicLen", "dwThermalInfoLen", "pPicBuff", "pThermalPicBuff", "pThermalInfoBuff", "struHighestPoint",
			 "fToleranceTemperature", "dwAlertFilteringTime", "dwAlarmFilteringTime", "dwTemperatureSuddenChangeCycle", "fTemperatureSuddenChangeValue", "byPicTransType", "byRes");
        }
	}

	public static class NET_VCA_FACESNAP_ADDINFO extends Structure
	{
        public NET_VCA_RECT  struFacePicRect;
        public int    iSwingAngle;
        public int    iTiltAngle;
        public int    dwPupilDistance;
        public byte   byBlockingState;
        public byte   byRes1;
        public byte   byIsAbnomalTemperature;
        public byte   byThermometryUnit;
        public NET_DVR_TIME_EX  struEnterTime;   
        public NET_DVR_TIME_EX  struExitTime;   
        public float       fFaceTemperature;
		public float       fAlarmTemperature;
		public byte[] byRes = new byte[472];
		public NET_VCA_FACESNAP_ADDINFO(Pointer p){
			super(p);
		}

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("struFacePicRect", "iSwingAngle", "iTiltAngle", "dwPupilDistance", "byBlockingState", "byRes1",
                    "byIsAbnomalTemperature","byThermometryUnit", "struEnterTime", "struExitTime", "fFaceTemperature", "fAlarmTemperature","byRes");
        }
	}


	public static class NET_VCA_FACESNAP_RESULT extends Structure
	{
		public int dwSize;
		public int dwRelativeTime;
		public int dwAbsTime;
		public int dwFacePicID;
		public int dwFaceScore;
		public NET_VCA_TARGET_INFO struTargetInfo = new NET_VCA_TARGET_INFO();
		public NET_VCA_RECT struRect = new NET_VCA_RECT();
		public NET_VCA_DEV_INFO struDevInfo = new NET_VCA_DEV_INFO();
		public int dwFacePicLen;
		public int dwBackgroundPicLen;
		public byte bySmart;
		public byte byAlarmEndMark;
		public byte byRepeatTimes;
		public byte byUploadEventDataType;
		public NET_VCA_HUMAN_FEATURE struFeature = new NET_VCA_HUMAN_FEATURE();
		public float fStayDuration;
		public byte[]  sStorageIP = new byte[16];
		public short wStoragePort;
		public short wDevInfoIvmsChannelEx;
		public byte byFacePicQuality;
		public byte byUIDLen; 
		public byte byLivenessDetectionStatus;
		public byte byAddInfo;
		public ByteByReference pUIDBuffer; 
		public ByteByReference pAddInfoBuffer;
		public byte byTimeDiffFlag;     
		public byte cTimeDifferenceH;       
		public byte cTimeDifferenceM;     
		public byte byBrokenNetHttp;    
		public ByteByReference pBuffer1;
		public ByteByReference pBuffer2;
		public NET_VCA_FACESNAP_RESULT(Pointer p){
			super(p);
		}

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "dwRelativeTime", "dwAbsTime", "dwFacePicID", "dwFaceScore", "struTargetInfo",
                    "struRect","struDevInfo", "dwFacePicLen", "dwBackgroundPicLen", "bySmart", "byAlarmEndMark", "byRepeatTimes",
                    "byUploadEventDataType", "struFeature", "fStayDuration", "sStorageIP", "wStoragePort",
                    "wDevInfoIvmsChannelEx", "byFacePicQuality", "byUIDLen", "byLivenessDetectionStatus", "byAddInfo",
					 "pUIDBuffer", "pAddInfoBuffer", "byTimeDiffFlag", "cTimeDifferenceH", "cTimeDifferenceM", "byBrokenNetHttp", "pBuffer1", "pBuffer2");
        }
	}

	public static class NET_VCA_FACESNAP_INFO_ALARM extends Structure
	{
		public int dwRelativeTime;
		public int dwAbsTime;
		public int dwSnapFacePicID;
		public int dwSnapFacePicLen;
		public NET_VCA_DEV_INFO struDevInfo = new NET_VCA_DEV_INFO();
		public byte byFaceScore;
		public byte bySex;
		public byte byGlasses;
		public byte byAge;
		public byte byAgeDeviation;
		public byte[] byRes1 = new byte[3];
		public int dwUIDLen;
		public ByteByReference pUIDBuffer;
		public byte[] byRes = new byte[4];
		public ByteByReference pBuffer1;

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("dwRelativeTime", "dwAbsTime", "dwSnapFacePicID", "dwSnapFacePicLen", "struDevInfo", "byFaceScore",
                    "bySex","byGlasses", "byAge", "byAgeDeviation", "byRes1","dwUIDLen","pUIDBuffer","byRes","pBuffer1");
        }
	}

	public static class NET_DVR_AREAINFOCFG extends Structure
	{
		public short wNationalityID;
		public short wProvinceID;
		public short wCityID;
		public short wCountyID;
		public int dwCode;

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("wNationalityID", "wProvinceID", "wCityID", "wCountyID", "dwCode");
        }
	}

	public static class NET_VCA_HUMAN_ATTRIBUTE extends Structure
	{
		public byte bySex;
		public byte byCertificateType;
		public byte[] byBirthDate = new byte[MAX_HUMAN_BIRTHDATE_LEN];
		public byte[] byName = new byte[NAME_LEN];
		public NET_DVR_AREAINFOCFG struNativePlace = new NET_DVR_AREAINFOCFG();
		public byte[] byCertificateNumber = new byte[NAME_LEN];

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("bySex", "byCertificateType", "byBirthDate", "byName", "struNativePlace", "byCertificateNumber");
        }
	}

	public static class NET_VCA_BLACKLIST_INFO extends Structure
	{
		public int dwSize;
		public int dwRegisterID;
		public int dwGroupNo;
		public byte byType;
		public byte byLevel;
		public byte[] byRes1 = new byte[2];
		public NET_VCA_HUMAN_ATTRIBUTE struAttribute = new NET_VCA_HUMAN_ATTRIBUTE();
		public byte[] byRemark = new byte[NAME_LEN];
		public int dwFDDescriptionLen;
		public ByteByReference pFDDescriptionBuffer;
        public int dwFCAdditionInfoLen;
        public ByteByReference  pFCAdditionInfoBuffer;
		public byte[] byRes2 = new byte[4];

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "dwRegisterID", "dwGroupNo", "byType", "byLevel", "byRes1",
                    "struAttribute","byRemark", "dwFDDescriptionLen", "pFDDescriptionBuffer", "dwFCAdditionInfoLen", "pFCAdditionInfoBuffer", "byRes2");
        }
	}

	public static class NET_VCA_BLACKLIST_INFO_ALARM extends Structure
	{
		public NET_VCA_BLACKLIST_INFO struBlackListInfo = new NET_VCA_BLACKLIST_INFO();
		public int dwBlackListPicLen;
		public int dwFDIDLen;
		public ByteByReference pFDID;
		public int dwPIDLen;
		public ByteByReference pPID;
		public short wThresholdValue;
		public byte[] byRes = new byte[2];
		public ByteByReference pBuffer1;

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("struBlackListInfo", "dwBlackListPicLen", "dwFDIDLen", "pFDID", "dwPIDLen", "pPID",
                    "wThresholdValue","byRes", "pBuffer1");
        }
	}

	public static class NET_VCA_FACESNAP_MATCH_ALARM extends Structure
	{
		public int dwSize;
		public float fSimilarity;
		public NET_VCA_FACESNAP_INFO_ALARM struSnapInfo = new NET_VCA_FACESNAP_INFO_ALARM();
		public NET_VCA_BLACKLIST_INFO_ALARM struBlackListInfo = new NET_VCA_BLACKLIST_INFO_ALARM();
		public byte[] sStorageIP = new byte[16];
		public short wStoragePort;
		public byte byMatchPicNum;
		public byte byPicTransType;
		public int dwSnapPicLen;
		public ByteByReference pSnapPicBuffer;
		public NET_VCA_RECT struRegion = new NET_VCA_RECT();
		public int dwModelDataLen;
		public ByteByReference pModelDataBuffer;
		public byte[] byRes = new byte[8];
		public NET_VCA_FACESNAP_MATCH_ALARM(Pointer p){
			super(p);
	    }

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "fSimilarity", "struSnapInfo", "struBlackListInfo", "sStorageIP", "wStoragePort",
                    "byMatchPicNum","byPicTransType", "dwSnapPicLen", "pSnapPicBuffer","struRegion","dwModelDataLen",
                    "pModelDataBuffer","byRes");
        }
	}

	public static class  struStartFrame extends Structure{
		public int dwRelativeTime;
		public int dwAbsTime;
		public byte[] byRes = new byte[92];

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwRelativeTime","dwAbsTime", "byRes");
		}
	}

	public static class  struStartTime extends Structure{
		public NET_DVR_TIME tmStart = new NET_DVR_TIME();
		public NET_DVR_TIME tmEnd = new NET_DVR_TIME();
		public byte[] byRes = new byte[92];

		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("tmStart","tmEnd", "byRes");
		}
	}

	public static class unionStartModeParam extends Union{
		public struStartFrame struStartFrame = new struStartFrame();
		public struStartTime struStartTime = new struStartTime();
	}

	public static class NET_DVR_PDC_ALRAM_INFO extends Structure
	{
		public int dwSize;
		public byte byMode;
		public byte byChannel;
		public byte bySmart;
		public byte byRes1;
		public NET_VCA_DEV_INFO struDevInfo = new NET_VCA_DEV_INFO();
		public unionStartModeParam unionStartModeParam= new unionStartModeParam();
		public int dwLeaveNum;
		public int dwEnterNum;
		public byte byBrokenNetHttp;
		public byte byRes3;
		public short wDevInfoIvmsChannelEx;
		public int dwPassingNum;
		public byte[] byRes = new byte[32];
		public NET_DVR_PDC_ALRAM_INFO(Pointer p){
			super(p);
	    }

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "byMode", "byChannel", "bySmart", "byRes1", "struDevInfo","unionStartModeParam",
                    "dwLeaveNum","dwEnterNum", "byBrokenNetHttp", "byRes3", "wDevInfoIvmsChannelEx","dwPassingNum","byRes");
        }

	}

	public static class NET_DVR_FACELIB_COND extends Structure
	{
		public int dwSize;
		public byte[] szFDID = new byte[NET_SDK_MAX_FDID_LEN];
		public byte byConcurrent;
		public byte[] byRes = new byte[127];

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "szFDID", "byConcurrent", "byRes");
        }
	}

	public class NET_SDK_UPLOAD_TYPE {
        public static final int UPGRADE_CERT_FILE = 0; 
        public static final int UPLOAD_CERT_FILE = 1;
        public static final int  TRIAL_CERT_FILE = 2;
        public static final int CONFIGURATION_FILE = 3;
        public static final int UPLOAD_RECORD_FILE = 4 ; 
        public static final int SCENE_CONFIGURATION_FILE = 5; 
        public static final int UPLOAD_PICTURE_FILE = 6;  
        public static final int UPLOAD_VIOLATION_FILE = 7;  
        public static final int UPLOAD_TG_FILE = 8;  
        public static final int UPLOAD_DATA_TO_DB = 9;       
        public static final int UPLOAD_BACKGROUND_PIC = 10; 
        public static final int UPLOAD_CALIBRATION_FILE = 11; 
        public static final int UPLOAD_TME_FILE = 12; 
        public static final int UPLOAD_VEHICLE_BLACKWHITELST_FILE = 13;
        public static final int UPLOAD_PICTURE_TO_CLOUD = 15;    
        public static final int UPLOAD_VIDEO_FILE = 16;  
        public static final int UPLOAD_SCREEN_FILE = 17;    
        public static final int UPLOAD_PUBLISH_MATERIAL = 18;    
        public static final int UPLOAD_PUBLISH_UPGRADE_FILE = 19;    
        public static final int UPLOAD_RING_FILE = 20;   
        public static final int UPLOAD_ENCRYPT_CERT = 21;   
        public static final int UPLOAD_THERMOMETRIC_FILE = 22; 
        public static final int UPLOAD_SUBBRAND_FILE = 23; 
        public static final int UPLOAD_LED_CHECK_FILE = 24;    
        public static final int BATCH_UPLOAD_PICTURE_FILE = 25; 
        public static final int UPLOAD_EDID_CFG_FILE = 26; 
        public static final int UPLOAD_PANORAMIC_STITCH = 27;
        public static final int UPLOAD_BINOCULAR_COUNTING = 28;
        public static final int UPLOAD_AUDIO_FILE = 29;  
        public static final int UPLOAD_PUBLISH_THIRD_PARTY_FILE = 30;  
        public static final int UPLOAD_DEEPEYES_BINOCULAR = 31;
        public static final int UPLOAD_CERTIFICATE_BLACK_LIST = 32;  
        public static final int UPLOAD_HD_CAMERA_CORRECT_TABLE = 33;
        public static final int UPLOAD_FD_DATA = 35;
        public static final int UPLOAD_FACE_DATA = 36;
        public static final int UPLOAD_FACE_ANALYSIS_DATA = 37;
        public static final int UPLOAD_FILEVOLUME_DATA = 38;
        public static final int IMPORT_DATA_TO_FACELIB = 39;
        public static final int UPLOAD_LEFTEYE_4K_CALIBFILE = 40;
        public static final int UPLOAD_SECURITY_CFG_FILE = 41;
        public static final int UPLOAD_RIGHT_CONTROLLER_AUDIO = 42; 
        public static final int UPLOAD_MODBUS_CFG_FILE = 43; 
        public static final int UPLOAD_NOTICE_VIDEO_DATA = 44; 
        public static final int UPLOAD_RS485_PROTOCOL_DLL_FILE = 45;
        public static final int UPLOAD_PIC_BY_BUF = 46;
        public static final int UPLOAD_CLIENT_CALIBFILE = 47;
        public static final int UPLOAD_HD_CAMERA_CORRECT_TABLE_3200W = 48;
        public static final int UPLOAD_DOOR_CONTENT = 49;
        public static final int UPLOAD_ASR_CONTROL_FILE = 50; 
        public static final int UPLOAD_APP_FILE = 51;
        public static final int UPLOAD_AI_ALGORITHM_MODEL = 52;    
        public static final int UPLOAD_PUBLISH_PROGRAM_THUMBNAIL = 53;   
        public static final int UPLOAD_PUBLISH_TEMPLATE_THUMBNAIL = 54;   
	}

	public class NET_SDK_DOWNLOAD_TYPE {
        public static final int  NET_SDK_DOWNLOAD_CERT = 0;        
        public static final int  NET_SDK_DOWNLOAD_IPC_CFG_FILE = 1;
        public static final int  NET_SDK_DOWNLOAD_BASELINE_SCENE_PIC = 2; 
        public static final int  NET_SDK_DOWNLOAD_VQD_ALARM_PIC = 3;       
        public static final int  NET_SDK_DOWNLOAD_CONFIGURATION_FILE = 4;  
        public static final int  NET_SDK_DOWNLOAD_SCENE_CONFIGURATION_FILE = 5; 
        public static final int  NET_SDK_DOWNLOAD_FILE_FORM_DB = 6;                
        public static final int  NET_SDK_DOWNLOAD_TME_FILE = 7;  
        public static final int  NET_SDK_DOWNLOAD_VEHICLE_BLACKWHITELST_FILE = 8; 
        public static final int  NET_SDK_DOWNLOAD_GUID_FILE = 9; 
        public static final int  NET_SDK_DOWNLOAD_FILE_FORM_CLOUD = 10;    
        public static final int  NET_SDK_DOWNLOAD_PICTURE = 11; 
        public static final int  NET_SDK_DOWNLOAD_VIDEO = 12; 
        public static final int   NET_DVR_DOWNLOAD_SCREEN_FILE = 13; 
        public static final int  NET_SDK_DOWNLOAD_PUBLISH_MATERIAL = 14;   
        public static final int  NET_SDK_DOWNLOAD_THERMOMETRIC_FILE = 15;
        public static final int  NET_SDK_DOWNLOAD_LED_CHECK_FILE = 16;
        public static final int  NET_SDK_DOWNLOAD_VEHICLE_INFORMATION = 17;
        public static final int  NET_SDK_DOWNLOAD_CERTIFICATE_BLACK_LIST_TEMPLET = 18; 
        public static final int  NET_SDK_DOWNLOAD_LOG_FILE = 19; 
        public static final int  NET_SDK_DOWNLOAD_FILEVOLUME_DATA = 20;
        public static final int  NET_SDK_DOWNLOAD_FD_DATA = 21;
        public static final int  NET_SDK_DOWNLOAD_SECURITY_CFG_FILE = 22;
        public static final int  NET_SDK_DOWNLOAD_PUBLISH_SCHEDULE = 23; 
        public static final int  NET_SDK_DOWNLOAD_RIGHT_CONTROLLER_AUDIO = 24; 
        public static final int  NET_SDK_DOWNLOAD_MODBUS_CFG_FILE = 25; 
        public static final int  NET_SDK_DOWNLOAD_RS485_PROTOCOL_DLL_FILE = 26; 
        public static final int  NET_SDK_DOWNLOAD_CLUSTER_MAINTENANCE_LOG = 27; 
        public static final int  NET_SDK_DOWNLOAD_SQL_ARCHIVE_FILE = 28; 
        public static final int  NET_SDK_DOWNLOAD_SUBWIND_STREAM = 29;
        public static final int  NET_SDK_DOWNLOAD_DEVTYPE_CALIBFILE = 30;
        public static final int  NET_SDK_DOWNLOAD_HD_CAMERA_CORRECT_TABLE = 31;
        public static final int  NET_SDK_DOWNLOAD_CLIENT_CALIBFILE = 32;
        public static final int  NET_SDK_DOWNLOAD_FOUE_CAMERAS_PICTURES = 33;
        public static final int  NET_SDK_DOWNLOAD_DOOR_CONTENT = 34; 
        public static final int  NET_SDK_DOWNLOAD_PUBLISH_MATERIAL_THUMBNAIL = 35;   
        public static final int  NET_SDK_DOWNLOAD_PUBLISH_PROGRAM_THUMBNAIL = 36;
        public static final int  NET_SDK_DOWNLOAD_PUBLISH_TEMPLATE_THUMBNAIL = 37;
	}

	public static class NET_DVR_SEND_PARAM_IN extends Structure
	{
		public Pointer pSendData;
		public int dwSendDataLen;
		public NET_DVR_TIME_V30 struTime;
		public byte byPicType;
		public byte[] byRes1 = new byte[3];
		public int dwPicManageNo;
		public byte[] sPicName = new byte[NAME_LEN];
		public int dwPicDisplayTime;
		public Pointer pSendAppendData;
		public int dwSendAppendDataLen;
		public byte[]  byRes = new byte[192];

		 @Override
	        protected List<String> getFieldOrder() {
	        // TODO Auto-generated method stub
	            return Arrays.asList("pSendData", "dwSendDataLen", "struTime", "byPicType", "byRes1", "dwPicManageNo",
	                    "sPicName","dwPicDisplayTime", "pSendAppendData", "dwSendAppendDataLen", "byRes");
	        }
	}

	public static class NET_DVR_UPLOAD_FACE_DATA_OUT extends Structure
	{
		public byte[] szPicID = new byte[NET_SDK_MAX_PICID_LEN];
		public byte[] byRes = new byte[128];

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("szPicID", "byRes");
        }
	}

	public static class NET_DVR_UPLOAD_FILE_RET extends Structure
	{
		public byte[] sUrl = new byte[MAX_UPLOADFILE_URL_LEN];
		public byte[] byRes = new byte[260];

		@Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("sUrl", "byRes");
        }
	}

	public static class NET_DVR_EZVIZ_ACCESS_CFG extends Structure
	{
	    public int dwSize;
	    public byte byEnable;
	    public byte byDeviceStatus;
	    public byte byAllowRedirect;
	    public byte[] byDomainName = new byte[MAX_DOMAIN_NAME];
	    public byte byRes1;
	    public byte[] byVerificationCode = new byte[NET_SDK_MAX_VERIFICATION_CODE_LEN];
	    public byte byNetMode;
	    public byte[] byRes = new byte[411];

	    @Override
        protected List<String> getFieldOrder() {
        // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "byEnable", "byDeviceStatus", "byAllowRedirect", "byDomainName", "byRes1",
                    "byVerificationCode","byNetMode", "byRes");
        }
	}


	public static class NET_DVR_SUBSYSTEM_BASIC_INFO_RESPONSE extends Structure
	{
		public int		dwSize;
		public int		dwErrorCode;
		public byte  byDevNo;
		public byte bySubSystemNo ;
		public byte [] byRes = new byte[30];

		 public NET_DVR_SUBSYSTEM_BASIC_INFO_RESPONSE(Pointer p){
				super(p);
		    }

		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "dwErrorCode", "byDevNo", "bySubSystemNo", "byRes");
		}
	}


	public static class NET_DVR_AUDIO_INFO extends Structure
	{
		public int		dwSize;
		public byte byAudioChanType;
		public byte [] byRes1 = new byte[3];
		public int		dwAudioNo;
		public byte []byRes2= new byte[16];

		 public NET_DVR_AUDIO_INFO(Pointer p){
				super(p);
		    }

		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "byAudioChanType", "byRes1", "dwAudioNo", "byRes2");
		}
	}

	public static class NET_DVR_DISPLAYPARAM extends Structure
	{
		public int dwDisplayNo;
		public byte  byDispChanType;
		public byte  byRes[] = new byte[11];

		 public NET_DVR_DISPLAYPARAM(Pointer p){
				super(p);
		    }

		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwDisplayNo", "byDispChanType", "byRes");
		}
	}



	public static class NET_DVR_DISPLAYCFG extends Structure
	{
		public int  dwSize ;
		public NET_DVR_DISPLAYPARAM []struDisplayParam = new NET_DVR_DISPLAYPARAM[MAX_DISPLAY_NUM] ;
	    public byte  []byRes = new byte[128];

	    protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("dwSize", "struDisplayParam", "byRes");
		}
	}


	public static class  NET_DVR_VIDEOWALLDISPLAYPOSITION extends Structure
	{
		public int   dwSize ;
		public byte	byEnable ;
		public byte	[]byRes1 = new byte[3] ;
		public int  	 dwVideoWallNo ;
		public int   dwDisplayNo;
		public NET_DVR_RECTCFG_EX struRectCfg = new NET_DVR_RECTCFG_EX();
		public byte	[]byRes2 = new byte[64];

		 protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "byEnable", "byRes1","dwVideoWallNo", "dwDisplayNo", "struRectCfg", "byRes2");
			}
	}

	public static class NET_DVR_MATRIX_DECCHAN_CONTROL extends Structure
	{
		public int	dwSize;
		public byte	byDecChanScaleStatus;
		public byte	byDecodeDelay;
		public byte	byEnableSpartan;
		public byte	byLowLight;
		public byte	byNoiseReduction;
		public byte	byDefog;
		public byte	byEnableVcaDec;
		public byte byEnableAudio;
		public int  dwAllCtrlType;
		public byte	[]byRes = new byte[56];

		protected List<String> getFieldOrder() {
				// TODO Auto-generated method stub
				return Arrays.asList("dwSize", "byDecChanScaleStatus", "byDecodeDelay","byEnableSpartan", "byLowLight", "byNoiseReduction", "byDefog",
						"byEnableVcaDec", "byEnableAudio", "dwAllCtrlType", "byRes");
				}
	}

    public static class NET_DVR_CURTRIGGERMODE extends Structure{
        public int      dwSize;
        public int      dwTriggerType;
        public byte[]   byRes = new byte[24];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "dwTriggerType", "byRes");
        }
    }

    public static class NET_ITC_RS485_ACCESS_INFO_COND extends Structure{
        public int      dwSize;
        public int      dwChannel;
        public int      dwTriggerModeType;
        public byte     byAssociateRS485No;
        public byte[]   byRes = new byte[15];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "dwChannel", "dwTriggerModeType", "byAssociateRS485No", "byRes");
        }
    }

    public static class NET_ITC_RADAR_PARAM extends Structure{
        public byte      byRadarType;
        public byte      byLevelAngle;
        public short     wRadarSensitivity;
        public short     wRadarSpeedValidTime;
        public byte[]    byRes1 = new byte[2];
        public float     fLineCorrectParam;
        public int       iConstCorrectParam;
        public byte[]    byRes2 = new byte[8];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byRadarType", "byLevelAngle", "wRadarSensitivity", "wRadarSpeedValidTime", "byRes1", "fLineCorrectParam", "iConstCorrectParam", "byRes2");
        }
    }

    public static class NET_ITC_RADAR_INFO_PARAM extends Structure{
        public NET_ITC_RADAR_PARAM     struRadarParam = new NET_ITC_RADAR_PARAM();
        public byte                    byAssociateLaneNo;
        public byte[]                  byRes = new byte[103];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("struRadarParam", "byAssociateLaneNo", "byRes");
        }
    }

    public static class NET_ITC_ACCESS_DEVINFO_PARAM_UNION extends Union
    {
        public byte[]                       uLen = new byte[128];
        public NET_ITC_RADAR_INFO_PARAM     struRadarInfoParam = new NET_ITC_RADAR_INFO_PARAM();
    }

    public static class NET_ITC_RS485_ACCESS_CFG extends Structure{
        public int                                   dwSize;
        public byte                                  byModeType;
        public byte[]                                byRes = new byte[3];
        public NET_ITC_ACCESS_DEVINFO_PARAM_UNION    uITCAccessDevinfoParam = new NET_ITC_ACCESS_DEVINFO_PARAM_UNION();
        public byte[]                                byRes1 = new byte[12];

        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "byModeType", "byRes", "uITCAccessDevinfoParam", "byRes1");
        }
    }

    public static class NET_ITC_PLATE_RECOG_PARAM extends Structure{
        public byte[]   byDefaultCHN = new byte[MAX_CHJC_NUM];
        public byte     byEnable;
        public int      dwRecogMode;
        public byte     byVehicleLogoRecog;
        public byte     byProvince;
        public byte     byRegion;
        public byte     byRes1;
        public short    wPlatePixelWidthMin;
        public short    wPlatePixelWidthMax;
        public byte[]   byRes = new byte[24];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byDefaultCHN", "byEnable", "dwRecogMode", "byVehicleLogoRecog", "byProvince", "byRegion", "byRes1", "wPlatePixelWidthMin", "wPlatePixelWidthMax", "byRes");
        }
    }

    public static class NET_ITC_INTERVAL_PARAM extends Structure{
        public byte      byIntervalType;
        public byte[]    byRes1 = new byte[3];
        public short[]   wInterval = new short[MAX_INTERVAL_NUM];
        public byte[]    byRes = new byte[8];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byIntervalType", "byRes1", "wInterval", "byRes");
        }
    }

    public static class NET_ITC_POLYGON extends Structure{
        public int                 dwPointNum;
        public NET_VCA_POINT[]     struPos = (NET_VCA_POINT[])new NET_VCA_POINT().toArray(ITC_MAX_POLYGON_POINT_NUM);

        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwPointNum", "struPos");
        }
    }

    public static class unionRegion extends Union{
        public NET_VCA_RECT     struRect = new NET_VCA_RECT();
        public NET_ITC_POLYGON  struPolygon = new NET_ITC_POLYGON();
    }

    public static class NET_ITC_PLATE_RECOG_REGION_PARAM extends Structure{
        public byte          byMode;
        public byte[]        byRes1 = new byte[3];
        public unionRegion   uRegion = new unionRegion();
        public byte[]        byRes = new byte[16];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byMode", "byRes1", "uRegion", "byRes");
        }
    }

    public static class NET_ITC_LANE_PARAM extends Structure{
        public byte                                byEnable;
        public byte                                byRelatedDriveWay;
        public short                               wDistance;
        public short                               wTrigDelayTime;
        public byte                                byTrigDelayDistance;
        public byte                                bySpeedCapEn;
        public byte                                bySignSpeed;
        public byte                                bySpeedLimit;
        public byte                                bySnapTimes;
        public byte                                byOverlayDriveWay;
        public NET_ITC_INTERVAL_PARAM              struInterval = new NET_ITC_INTERVAL_PARAM();
        public byte[]                              byRelatedIOOut = new byte[MAX_IOOUT_NUM];
        public byte                                byFlashMode;
        public byte                                byCartSignSpeed;
        public byte                                byCartSpeedLimit;
        public byte                                byRelatedIOOutEx;
        public NET_ITC_PLATE_RECOG_REGION_PARAM[]  struPlateRecog = (NET_ITC_PLATE_RECOG_REGION_PARAM[])new NET_ITC_PLATE_RECOG_REGION_PARAM().toArray(MAX_LANEAREA_NUM);
        public byte                                byLaneType;
        public byte                                byUseageType;
        public byte                                byRelaLaneDirectionType;
        public byte                                byLowSpeedLimit;
        public byte                                byBigCarLowSpeedLimit;
        public byte                                byLowSpeedCapEn;
        public byte                                byEmergencyCapEn;
        public byte[]                              byRes = new byte[9];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byEnable", "byRelatedDriveWay", "wDistance", "wTrigDelayTime", "byTrigDelayDistance", "bySpeedCapEn", "bySignSpeed", "bySpeedLimit", "bySnapTimes", "byOverlayDriveWay", "struInterval", "byRelatedIOOut", "byFlashMode", "byCartSignSpeed", "byCartSpeedLimit", "byRelatedIOOutEx", "struPlateRecog", "byLaneType", "byUseageType", "byRelaLaneDirectionType", "byLowSpeedLimit", "byBigCarLowSpeedLimit", "byLowSpeedCapEn", "byEmergencyCapEn", "byRes");
        }
    }

    public static class NET_ITC_POST_RS485_RADAR_PARAM extends Structure{
        public byte                          byRelatedLaneNum;
        public byte[]                        byRes1 = new byte[3];
        public NET_ITC_PLATE_RECOG_PARAM     struPlateRecog = new NET_ITC_PLATE_RECOG_PARAM();
        public NET_ITC_LANE_PARAM[]          struLane = (NET_ITC_LANE_PARAM[])new NET_ITC_LANE_PARAM().toArray(MAX_ITC_LANE_NUM);
        public NET_ITC_RADAR_PARAM           struRadar = new NET_ITC_RADAR_PARAM();
        public byte[]                        byRes = new byte[32];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byRelatedLaneNum", "byRes1", "struPlateRecog", "struLane", "struRadar", "byRes");
        }
    }

    public static class NET_ITC_TRIGGER_PARAM_UNION extends Union{
        public int[]                              uLen = new int[1070];
        public NET_ITC_POST_RS485_RADAR_PARAM     struPostRadar = new NET_ITC_POST_RS485_RADAR_PARAM();
    }

    public static class NET_ITC_SINGLE_TRIGGERCFG extends Structure{
        public byte                            byEnable;
        public byte[]                          byRes1 = new byte[3];
        public int                             dwTriggerType;
        public NET_ITC_TRIGGER_PARAM_UNION     uTriggerParam = new NET_ITC_TRIGGER_PARAM_UNION();
        public byte[]                          byRes = new byte[64];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byEnable", "byRes1", "dwTriggerType", "uTriggerParam", "byRes");
        }
    }

    public static class NET_ITC_TRIGGERCFG extends Structure{
        public int                           dwSize;
        public NET_ITC_SINGLE_TRIGGERCFG     struTriggerParam = new NET_ITC_SINGLE_TRIGGERCFG();
        public byte[]                        byRes = new byte[32];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "struTriggerParam", "byRes");
        }
    }

    public static class NET_DVR_SHOWSTRINGINFO extends Structure{
        public short      wShowString;
        public short      wStringSize;
        public short      wShowStringTopLeftX;
        public short      wShowStringTopLeftY;
        public byte[]     sString = new byte[44];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("wShowString", "wStringSize", "wShowStringTopLeftX", "wShowStringTopLeftY", "sString");
        }
    }

    public static class NET_DVR_SHOWSTRING_V30 extends Structure{
        public int                       dwSize;
        public NET_DVR_SHOWSTRINGINFO[]  struStringInfo = (NET_DVR_SHOWSTRINGINFO[])new NET_DVR_SHOWSTRINGINFO().toArray(MAX_STRINGNUM_V30);

        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "struStringInfo");
        }
    }

    public static class NET_DVR_HANDLEEXCEPTION_V41 extends Structure{
        public int                       dwHandleType;
        public byte[]            byRes = new byte[16580];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwHandleType", "byRes");
        }
    }

    public static class NET_DVR_EVENT_TRIGGER extends Structure{
        public int                       dwSize;
        public NET_DVR_HANDLEEXCEPTION_V41  struHandleException = new NET_DVR_HANDLEEXCEPTION_V41();
        public byte[]            byRes = new byte[14592];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "struHandleException", "byRes");
        }
    }

    public static class NET_DVR_SCHEDTIME extends Structure{
        public byte          byStartHour;
        public byte 		 byStartMin;
        public byte          byStopHour;
        public byte          byStopMin;
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byStartHour", "byStartMin", "byStopHour", "byStopMin");
        }
    }

    public static class NET_DVR_SCHEDTIME_DAYS extends Structure{
    	public NET_DVR_SCHEDTIME[]  struSchedTimeDays = (NET_DVR_SCHEDTIME[])new NET_DVR_SCHEDTIME().toArray(MAX_DAYS);
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("struSchedTimeDays");
        }
    }

    public static class NET_DVR_EVENT_SCHEDULE extends Structure{
        public int                       dwSize;
        public NET_DVR_SCHEDTIME_DAYS[]  struAlarmTime = (NET_DVR_SCHEDTIME_DAYS[])new NET_DVR_SCHEDTIME_DAYS().toArray(MAX_TIMESEGMENT_V30);
        public byte[]            byRes = new byte[160];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "struHandleException", "byRes");
        }
    }

    public static class NET_DVR_FIND_PICTURE_PARAM extends Structure{
        public int             dwSize;
        public int             lChannel;
        public byte            byFileType;
        public byte            byNeedCard;
        public byte            byProvince;
        public byte            byRes1;
        public byte[]          sCardNum = new byte[CARDNUM_LEN_V30];
        public NET_DVR_TIME    struStartTime = new NET_DVR_TIME();
        public NET_DVR_TIME    struStopTime = new NET_DVR_TIME();
        public int             dwTrafficType;
        public int             dwVehicleType;
        public int             dwIllegalType;
        public byte            byLaneNo;
        public byte            bySubHvtType;
        public byte[]          byRes2 = new byte[2];
        public byte[]          sLicense = new byte[MAX_LICENSE_LEN];
        public byte            byRegion;
        public byte            byCountry;
        public byte[]          byRes3 = new byte[6];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "lChannel", "byFileType", "byNeedCard", "byProvince", "byRes1", "sCardNum", "struStartTime", "struStopTime", "dwTrafficType", "dwVehicleType", "dwIllegalType", "byLaneNo", "bySubHvtType", "byRes2", "sLicense", "byRegion", "byCountry", "byRes3");
        }
    }

    public static class NET_DVR_FACE_EXTRA_INFO extends Structure{
        public NET_VCA_RECT[] struVcaRect = (NET_VCA_RECT[])new NET_VCA_RECT().toArray(MAX_FACE_PIC_NUM);
        public byte[]         byRes = new byte[64];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("struVcaRect", "byRes");
        }
    }

    public static class NET_DVR_PIC_EXTRA_INFO_UNION extends Union{
        public byte[]   byUnionLen = new byte[544];
        public          NET_DVR_FACE_EXTRA_INFO   struFaceExtraInfo = new NET_DVR_FACE_EXTRA_INFO();
    }

    public static class NET_DVR_FIND_PICTURE_V40 extends Structure{
        public byte[]                           sFileName = new byte[PICTURE_NAME_LEN];
        public NET_DVR_TIME                     struTime = new NET_DVR_TIME();
        public int                              dwFileSize;
        public byte[]                           sCardNum = new byte[CARDNUM_LEN_V30];
        public byte                             byPlateColor;
        public byte                             byVehicleLogo;
        public byte                             byFileType;
        public byte                             byRecogResult ;
        public byte[]                           sLicense = new byte[MAX_LICENSE_LEN];
        public byte                             byEventSearchStatus;
        public byte[]                           byRes = new byte[75];
        public NET_DVR_PIC_EXTRA_INFO_UNION     uPicExtraInfo = new NET_DVR_PIC_EXTRA_INFO_UNION();
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("sFileName", "struTime", "dwFileSize", "sCardNum","byPlateColor", "byVehicleLogo","byFileType", "byRecogResult", "sLicense", "byEventSearchStatus", "byRes", "uPicExtraInfo");
        }
    }

    public static class NET_DVR_DEVICE_RUN_STATUS extends Structure{
    	public int 		dwSize;
    	public int 		dwMemoryTotal;
    	public int 		dwMemoryUsage;
    	public byte 	byCPUUsage;
    	public byte 	byMainFrameTemp;
    	public byte 	byBackPanelTemp;
    	public byte 	byRes1;
    	public byte[] 	byLeftDecResource = new byte[32];
    	public float 	fNetworkFlow;
    	public byte[]	byRes2 = new byte[88];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "dwMemoryTotal", "dwMemoryUsage", "byCPUUsage","byMainFrameTemp",
            		"byBackPanelTemp","byRes1", "byLeftDecResource", "fNetworkFlow", "byRes2");
        }
    }

    public static class NET_DVR_LOCAL_TCP_PORT_BIND_CFG extends Structure{
        public short    wLocalBindTcpMinPort;
        public short    wLocalBindTcpMaxPort;
        public byte[]   byRes = new byte[60];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("wLocalBindTcpMinPort", "wLocalBindTcpMaxPort", "byRes");
        }
    }

    public static class NET_DVR_LOCAL_UDP_PORT_BIND_CFG extends Structure{
        public short    wLocalBindUdpMinPort;
        public short    wLocalBindUdpMaxPort;
        public byte[]   byRes = new byte[60];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("wLocalBindUdpMinPort", "wLocalBindUdpMaxPort", "byRes");
        }
    }

    public static class NET_DVR_LOCAL_MEM_POOL_CFG extends Structure{
    	public int    dwAlarmMaxBlockNum;
    	public int    dwAlarmReleaseInterval;
    	public int    dwObjectReleaseInterval;
    	public byte[]    byRes = new byte[508];
    	@Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwAlarmMaxBlockNum", "dwAlarmReleaseInterval", "dwObjectReleaseInterval", "byRes");
        }
    }

    public static class NET_DVR_LOCAL_MODULE_RECV_TIMEOUT_CFG extends Structure{
    	public int        dwPreviewTime;
    	public int        dwAlarmTime;
    	public int        dwVodTime;
    	public int        dwElse;
    	public byte[]     byRes = new byte[512];
    	@Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwPreviewTime", "dwAlarmTime", "dwVodTime", "dwElse", "byRes");
        }
    }

    public static class NET_DVR_LOCAL_ABILITY_PARSE_CFG extends Structure{
    	public byte       byEnableAbilityParse;
    	public byte[]     byRes = new byte[127];
    	@Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byEnableAbilityParse", "byRes");
        }
    }

    public static class NET_DVR_LOCAL_TALK_MODE_CFG extends Structure{
        public byte      byTalkMode;
        public byte[]    byRes = new byte[127];
        @Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byTalkMode", "byRes");
        }
    }

    public static class NET_DVR_LOCAL_PROTECT_KEY_CFG extends Structure{
        public byte[]    byProtectKey = new byte[128];
        public byte[]    byRes = new byte[128];
        @Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byProtectKey", "byRes");
        }
    }

    public static class NET_DVR_LOCAL_CFG_VERSION extends Structure{
        public byte      byVersion;
        public byte[]    byRes = new byte[63];
        @Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byVersion", "byRes");
        }
    }

    public static class NET_DVR_RTSP_PARAMS_CFG extends Structure{
        public int       dwMaxBuffRoomNum;
        public byte      byUseSort;
        public byte[]    byRes = new byte[123];
        @Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwMaxBuffRoomNum", "byUseSort", "byRes");
        }
    }

    public static class NET_DVR_SIMXML_LOGIN extends Structure{
    	public byte     byLoginWithSimXml;
    	public byte[]   byRes = new byte[127];
    	@Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byLoginWithSimXml", "byRes");
        }
    }

    public static class NET_DVR_LOCAL_CHECK_DEV extends Structure{
    	public int   dwCheckOnlineTimeout;
    	public int   dwCheckOnlineNetFailMax;
    	public byte[]    byRes = new byte[256];
    	@Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwCheckOnlineTimeout", "dwCheckOnlineNetFailMax", "byRes");
        }
    }

    public static class NET_DVR_LOCAL_SECURITY extends Structure{
    	public byte     bySecurityLevel;
    	public byte[]   byRes = new byte[255];
    	@Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("bySecurityLevel", "byRes");
        }
    }

    public interface CHAR_ENCODE_CONVERT extends Callback {
		public void invoke(Pointer pInput, int dwInputLen,
				int dwInEncodeType, String pOutput, int dwOutputLen, int dwOutEncodeType);
	}

    public static class NET_DVR_LOCAL_BYTE_ENCODE_CONVERT extends Structure{
        public CHAR_ENCODE_CONVERT  fnCharConvertCallBack;
        public byte[]     byRes = new byte[256];
        @Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("fnCharConvertCallBack", "byRes");
        }
    }

    public static class NET_DVR_SOCKS_PROXY_PARA extends Structure{
        public byte[]  byIP = new byte[129];
        public byte    byAuthType;
        public short   wPort;
        public byte[]  byRes2 = new byte[64];
        @Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byIP", "byAuthType", "wPort", "byRes2");
        }
    }

    public static class NET_DVR_SOCKS_PROXYS extends Structure{
        public NET_DVR_SOCKS_PROXY_PARA[] struProxy =  new NET_DVR_SOCKS_PROXY_PARA[32];
        @Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("struProxy");
        }
    }

    public static class NET_DVR_LOCAL_LOG_CFG extends Structure{
        public short    wSDKLogNum;
        public byte[]     byRes = new byte[254];
        @Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("wSDKLogNum", "byRes");
        }
    }

    public static class NET_DVR_LOCAL_STREAM_CALLBACK_CFG extends Structure{
        public byte     byPlayBackEndFlag;
        public byte[]     byRes = new byte[255];
        @Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byPlayBackEndFlag", "byRes");
        }
    }

    public static class NET_DVR_LOCAL_GENERAL_CFG extends Structure{
    	public byte     byExceptionCbDirectly;
    	public byte     byNotSplitRecordFile;
    	public byte     byResumeUpgradeEnable;
    	public byte[]   byRes = new byte[5];
        public long     i64FileSize;
        public int      dwResumeUpgradeTimeout;
        public byte[]   byRes1 = new byte[236];
        @Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byExceptionCbDirectly", "byNotSplitRecordFile", "byResumeUpgradeEnable", "byRes", "i64FileSize", "dwResumeUpgradeTimeout", "byRes1");
        }
    }

    public static class NET_DVR_LOCAL_PTZ_CFG extends Structure{
    	public byte     byWithoutRecv;
    	public byte[]   byRes = new byte[63];
    	@Override
    	protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byWithoutRecv", "byRes");
        }
    }

  
    public static class NET_DVR_PTZPOS extends Structure
    {
       public  short wAction;
       public  short wPanPos;
       public  short wTiltPos;
       public  short wZoomPos;

        @Override
		protected List<String> getFieldOrder() {
                         // TODO Auto-generated method stub
                         return Arrays.asList("wAction", "wPanPos", "wTiltPos", "wZoomPos");
    	}
    }

    public static class NET_DVR_SCENE_INFO  extends Structure {
           public int dwSceneID;              
           public byte[] bySceneName = new byte[NAME_LEN]; 
           public byte byDirection;         
           public byte[] byRes1 = new byte[3];              
           public NET_DVR_PTZPOS  struPtzPos = new NET_DVR_PTZPOS();             
           public byte[] byRes2 = new byte[64] ;          

    @Override
           protected List<String> getFieldOrder() {
                         // TODO Auto-generated method stub
                         return Arrays.asList("dwSceneID", "bySceneName", "byDirection", "byRes1",
                                       "struPtzPos", "byRes2");
    	}
    }

	public static class NET_DVR_DIRECTION extends Structure {

		public NET_DVR_PTZPOS  struStartPoint = new NET_DVR_PTZPOS();      
		public NET_DVR_PTZPOS  struEndPoint = new NET_DVR_PTZPOS();           
		@Override
		protected List<String> getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList("struStartPoint", "struEndPoint");
		}
	}


    public static class NET_DVR_AID_INFO  extends Structure {
           public byte byRuleID;   
           public byte[] byRes1 = new byte[3];
           public byte[] byRuleName = new byte[NAME_LEN]; 
           public int dwAIDType;  
           public NET_DVR_DIRECTION struDirect = new NET_DVR_DIRECTION(); 
           public byte bySpeedLimit; 
           public byte byCurrentSpeed; 
           public byte byVehicleEnterState; 
           public byte[] byRes2 = new byte[37];  

    @Override
           protected List<String> getFieldOrder() {
                         // TODO Auto-generated method stub
                         return Arrays.asList("byRuleID", "byRes1", "byRuleName", "dwAIDType",
                                       "struDirect", "bySpeedLimit", "byCurrentSpeed", "byVehicleEnterState", "byRes2");
    	}
    }

    public static class NET_DVR_TFS_ALARM  extends Structure {
           public int dwSize;
           public int dwRelativeTime;
           public int dwAbsTime;
           public int dwIllegalType;
           public int dwIllegalDuration;
           public byte[] byMonitoringSiteID = new byte[MONITORSITE_ID_LEN];
           public byte[] byDeviceID = new byte[DEVICE_ID_LEN];
           public NET_VCA_DEV_INFO struDevInfo = new NET_VCA_DEV_INFO();
           public NET_DVR_SCENE_INFO struSceneInfo = new NET_DVR_SCENE_INFO();
           public NET_DVR_TIME_EX  struBeginRecTime = new NET_DVR_TIME_EX();
           public NET_DVR_TIME_EX  struEndRecTime = new NET_DVR_TIME_EX();
           public NET_DVR_AID_INFO  struAIDInfo = new NET_DVR_AID_INFO();
           public NET_DVR_PLATE_INFO     struPlateInfo = new NET_DVR_PLATE_INFO();
           public NET_DVR_VEHICLE_INFO    struVehicleInfo = new NET_DVR_VEHICLE_INFO();
           public int dwPicNum;
           public NET_ITS_PICTURE_INFO[]  struPicInfo = (NET_ITS_PICTURE_INFO[])new NET_ITS_PICTURE_INFO().toArray(8);
           public byte  bySpecificVehicleType;
           public byte  byLaneNo;
           public byte[] byRes1 = new byte[2];
           public NET_DVR_TIME_V30 struTime = new NET_DVR_TIME_V30();
           public int dwSerialNo;
           public byte byVehicleAttribute;
           public byte byPilotSafebelt;
           public byte byCopilotSafebelt;
           public byte byPilotSunVisor;
           public byte byCopilotSunVisor;
           public byte byPilotCall;
           public byte[] byRes2 = new byte[2];
           public byte[] byIllegalCode = new byte[MONITORSITE_ID_LEN];
           public byte[] byRes = new byte[68];
   		   public NET_DVR_TFS_ALARM(Pointer p){
		      super(p);
   		   }
    @Override
           protected List<String> getFieldOrder() {
                         // TODO Auto-generated method stub
                         return Arrays.asList("dwSize", "dwRelativeTime", "dwAbsTime", "dwIllegalType",
                                       "dwIllegalDuration", "byMonitoringSiteID", "byDeviceID", "struDevInfo", "struSceneInfo",
                                       "struBeginRecTime", "struEndRecTime", "struAIDInfo", "struPlateInfo",
                                       "struVehicleInfo", "dwPicNum", "struPicInfo", "bySpecificVehicleType",
                                       "byLaneNo", "byRes1", "struTime", "dwSerialNo",
                                       "byVehicleAttribute", "byPilotSafebelt", "byCopilotSafebelt", "byPilotSunVisor",
                                       "byCopilotSunVisor", "byPilotCall", "byRes2", "byIllegalCode",
                                       "byRes");
    	}
    }

    public static class NET_DVR_PICCFG_V40 extends Structure
    {
        public int dwSize;
        public byte[] sChanName = new byte[NAME_LEN];
        public int dwVideoFormat;
        public NET_DVR_VICOLOR struViColor;
        public int dwShowChanName;
        public short wShowNameTopLeftX;
        public short wShowNameTopLeftY;
        public int dwEnableHide;
        public NET_DVR_SHELTER[] struShelter = new NET_DVR_SHELTER[MAX_SHELTERNUM];
        public int dwShowOsd;
        public short wOSDTopLeftX;
        public short wOSDTopLeftY;
        public byte byOSDType;
        public byte byDispWeek;
        public byte byOSDAttrib;
        public byte byHourOSDType;
        public byte byFontSize;
        public byte byOSDColorType;
        public byte byAlignment;
        public byte byOSDMilliSecondEnable;
        public NET_DVR_VILOST_V40 struVILost;
        public NET_DVR_VILOST_V40 struAULost;
        public NET_DVR_MOTION_V40 struMotion;
        public NET_DVR_HIDEALARM_V40 struHideAlarm;
        public NET_DVR_RGB_COLOR struOsdColor;
        public int dwBoundary;
        public NET_DVR_RGB_COLOR struOsdBkColor;
        public byte byOSDBkColorMode;
        public byte[] byRes = new byte[115];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwSize", "sChanName", "dwVideoFormat", "struViColor", "dwShowChanName",
                    "wShowNameTopLeftX", "wShowNameTopLeftY", "dwEnableHide", "struShelter",
                    "dwShowOsd", "wOSDTopLeftX", "wOSDTopLeftY", "byOSDType", "byDispWeek",
                    "byOSDAttrib", "byHourOSDType", "byFontSize", "byOSDColorType", "byAlignment",
                    "byOSDMilliSecondEnable", "struVILost", "struAULost", "struMotion", "struHideAlarm",
                    "struOsdColor", "dwBoundary", "struOsdBkColor", "byOSDBkColorMode", "byRes");
        }
    }

    public static class NET_DVR_VICOLOR extends Structure
    {
        public NET_DVR_COLOR[] struShelter = new NET_DVR_COLOR[MAX_TIMESEGMENT_V30];
        public NET_DVR_SCHEDTIME[] struHandleTime = new NET_DVR_SCHEDTIME[MAX_TIMESEGMENT_V30];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("struShelter", "struHandleTime");
        }
    }

    public static class NET_DVR_COLOR extends Structure
    {
        public byte byBrightness;
        public byte byContrast;
        public byte bySaturation;
        public byte byHue;
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byBrightness", "byContrast", "bySaturation", "byHue");
        }
    }

    public static class NET_DVR_SHELTER extends Structure
    {
        public short wHideAreaTopLeftX;
        public short wHideAreaTopLeftY;
        public short wHideAreaWidth;
        public short wHideAreaHeight;
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("wHideAreaTopLeftX", "wHideAreaTopLeftY", "wHideAreaWidth", "wHideAreaHeight");
        }
    }

    public static class NET_DVR_VILOST_V40 extends Structure
    {
        public int dwEnableVILostAlarm;
        public int dwHandleType;
        public int dwMaxRelAlarmOutChanNum;
        public int[] dwRelAlarmOut = new int[MAX_ALARMOUT_V40];
        public NET_DVR_SCHEDTIMEWEEK[] struAlarmTime = new NET_DVR_SCHEDTIMEWEEK[MAX_DAYS];
        public byte byVILostAlarmThreshold;
        public byte[] byRes = new byte[63];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwEnableVILostAlarm", "dwHandleType", "dwMaxRelAlarmOutChanNum",
                    "dwRelAlarmOut", "struAlarmTime", "byVILostAlarmThreshold", "byRes");
        }
    }

    public static class NET_DVR_SCHEDTIMEWEEK extends Structure
    {
        public NET_DVR_SCHEDTIME[] struAlarmTime = new NET_DVR_SCHEDTIME[MAX_TIMESEGMENT_V30];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("struAlarmTime");
        }
    }

    public static class NET_DVR_MOTION_V40 extends Structure
    {
        public NET_DVR_MOTION_MODE_PARAM struMotionMode;
        public byte byEnableHandleMotion;
        public byte byEnableDisplay;
        public byte byConfigurationMode;
        public byte byKeyingEnable;
        public int dwHandleType;
        public int dwMaxRelAlarmOutChanNum;
        public int[] dwRelAlarmOut = new int[MAX_ALARMOUT_V40];
        public NET_DVR_SCHEDTIMEWEEK[] struAlarmTime = new NET_DVR_SCHEDTIMEWEEK[MAX_DAYS];
        public int dwMaxRecordChanNum;
        public int[] dwRelRecordChan = new int[MAX_CHANNUM_V40];
        public byte byDiscardFalseAlarm;
        public byte[] byRes = new byte[127];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("struMotionMode", "byEnableHandleMotion", "byEnableDisplay", "byConfigurationMode",
                    "byKeyingEnable", "dwHandleType", "dwMaxRelAlarmOutChanNum", "dwRelAlarmOut",
                    "struAlarmTime", "dwMaxRecordChanNum", "dwRelRecordChan", "byDiscardFalseAlarm", "byRes");
        }
    }

    public static class NET_DVR_MOTION_MODE_PARAM extends Structure
    {
        public NET_DVR_MOTION_SINGLE_AREA struMotionSingleArea;
        public NET_DVR_MOTION_MULTI_AREA struMotionMultiArea;
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("struMotionSingleArea", "struMotionMultiArea");
        }
    }

    public static class NET_DVR_MOTION_SINGLE_AREA extends Structure
    {
        public NET_DVR_MOTIONSCOPE[] byMotionScope = new NET_DVR_MOTIONSCOPE[64];
        public byte byMotionSensitive;
        public byte[] byRes = new byte[3];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byMotionScope", "byMotionSensitive", "byRes");
        }
    }

    public static class NET_DVR_MOTIONSCOPE extends Structure
    {
        public byte[] byMotionScope = new byte[96];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byMotionScope");
        }
    }

    public static class NET_DVR_MOTION_MULTI_AREA extends Structure
    {
        public byte byDayNightCtrl;
        public byte byAllMotionSensitive;
        public byte[] byRes = new byte[2];
        public NET_DVR_SCHEDULE_DAYTIME struScheduleTime;
        public NET_DVR_MOTION_MULTI_AREAPARAM[] struMotionMultiAreaParam = new NET_DVR_MOTION_MULTI_AREAPARAM[MAX_MULTI_AREA_NUM];
        public byte[] byRes1 = new byte[60];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byDayNightCtrl", "byAllMotionSensitive", "byRes",
                    "struScheduleTime", "struMotionMultiAreaParam", "byRes1");
        }
    }

    public static class NET_DVR_SCHEDULE_DAYTIME extends Structure
    {
        public NET_DVR_DAYTIME struStartTime;
        public NET_DVR_DAYTIME struStopTime;
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("struStartTime", "struStopTime");
        }
    }

    public static class NET_DVR_DAYTIME extends Structure
    {
        public byte byHour;
        public byte byMinute;
        public byte bySecond;
        public byte byRes;
        public short wMilliSecond;
        public byte[] byRes1 = new byte[2];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byHour", "byMinute", "bySecond", "byRes", "wMilliSecond",
                    "byRes1");
        }
    }

    public static class NET_DVR_MOTION_MULTI_AREAPARAM extends Structure
    {
        public byte byAreaNo;
        public byte[] byRes = new byte[3];
        public NET_VCA_RECT struRect;
        public NET_DVR_DNMODE struDayNightDisable;
        public NET_DVR_DNMODE struDayModeParam;
        public NET_DVR_DNMODE struNightModeParam;
        public byte[] byRes1 = new byte[8];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byAreaNo", "byRes", "struRect", "struDayNightDisable",
                    "struDayModeParam", "struNightModeParam", "byRes1");
        }
    }

    public static class NET_DVR_DNMODE extends Structure
    {
        public byte byObjectSize;
        public byte byMotionSensitive;
        public byte[] byRes = new byte[6];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byObjectSize", "byMotionSensitive", "byRes");
        }
    }

    public static class NET_DVR_HIDEALARM_V40 extends Structure
    {
        public int dwEnableHideAlarm;
        public short wHideAlarmAreaTopLeftX;
        public short wHideAlarmAreaTopLeftY;
        public short wHideAlarmAreaWidth;
        public short wHideAlarmAreaHeight;
        public int dwHandleType;
        public int dwMaxRelAlarmOutChanNum;
        public int[] dwRelAlarmOut = new int[MAX_ALARMOUT_V40];
        public NET_DVR_SCHEDTIMEWEEK[] struAlarmTime = new NET_DVR_SCHEDTIMEWEEK[MAX_DAYS];
        public byte[] byRes = new byte[64];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwEnableHideAlarm", "wHideAlarmAreaTopLeftX", "wHideAlarmAreaTopLeftY", "wHideAlarmAreaWidth",
                    "wHideAlarmAreaHeight", "dwHandleType", "dwMaxRelAlarmOutChanNum", "dwRelAlarmOut",
                    "struAlarmTime", "byRes");
        }
    }

    public static class NET_DVR_RGB_COLOR extends Structure
    {
        public byte byRed;
        public byte byGreen;
        public byte byBlue;
        public byte byRes;
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byRed", "byGreen", "byBlue", "byRes");
        }
    }

    public static class NET_DVR_TEMPERATURE_COLOR extends Structure
    {
        public byte byType;
        public byte[] byRes1 = new byte[3];
        public int iHighTemperature;
        public int iLowTemperature;
        public byte[] byRes = new byte[8];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byType", "byRes1", "iHighTemperature", "iLowTemperature", "byRes");
        }
    }

    public static class NET_DVR_THERMOMETRY_BASICPARAM extends Structure
    {
        public int   dwsize;
        public byte  byEnabled;
        public byte  byStreamOverlay;
        public byte  byPictureOverlay;
        public byte  byThermometryRange;
        public byte  byThermometryUnit;
        public byte  byThermometryCurve;
        public byte  byFireImageModea;
        public byte  byShowTempStripEnable;
        public float fEmissivity;
        public byte  byDistanceUnit;
        public byte  byEnviroHumidity;
        public byte[] byRes2 = new byte[2];
        public NET_DVR_TEMPERATURE_COLOR struTempColor;
        public int   iEnviroTemperature;
        public int   iCorrectionVolume;
        public byte  bySpecialPointThermType;
        public byte  byReflectiveEnabled;
        public short wDistance;
        public float fReflectiveTemperature;
        public float fAlert;
        public float fAlarm;
        public float fThermalOpticalTransmittance;
        public float fExternalOpticsWindowCorrection;
		public byte  byDisplayMaxTemperatureEnabled;
		public byte  byDisplayMinTemperatureEnabled;
		public byte  byDisplayAverageTemperatureEnabled;
		public byte  byThermometryInfoDisplayposition;
		public int   dwAlertFilteringTime;
		public int   dwAlarmFilteringTime;
		public byte  byemissivityMode; 
		public byte  bydisplayTemperatureInOpticalChannelEnabled;
		public byte  byDisplayCentreTemperatureEnabled;
        public byte[] byRes = new byte[49];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwsize", "byEnabled", "byStreamOverlay", "byPictureOverlay", "byThermometryRange", "byThermometryUnit",
                    "byThermometryCurve", "byFireImageModea", "byShowTempStripEnable", "fEmissivity", "byDistanceUnit", "byEnviroHumidity",
                    "byRes2", "struTempColor", "iEnviroTemperature", "iCorrectionVolume", "bySpecialPointThermType", "byReflectiveEnabled",
                    "wDistance", "fReflectiveTemperature", "fAlert", "fAlarm", "fThermalOpticalTransmittance", "fExternalOpticsWindowCorrection",
                    "byDisplayMaxTemperatureEnabled","byDisplayMinTemperatureEnabled","byDisplayAverageTemperatureEnabled",
					"byThermometryInfoDisplayposition","dwAlertFilteringTime","dwAlarmFilteringTime","byemissivityMode","bydisplayTemperatureInOpticalChannelEnabled",
					"byDisplayCentreTemperatureEnabled","byRes");
        }
    }

    public static class NET_DVR_THERMOMETRY_COND extends Structure
    {
    	public int  dwsize;
    	public int  dwChannel;
    	public short wPresetNo;
    	public byte[] byRes = new byte[62];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwsize", "dwChannel", "wPresetNo", "byRes");
        }
    }

    public static class NET_DVR_THERMOMETRY_PRESETINFO_PARAM extends Structure
    {
        public byte byEnabled;
        public byte byRuleID;
        public short wDistance;
        public float fEmissivity;
        public byte[] byRes = new byte[3];
        public byte byReflectiveEnabled;
        public float fReflectiveTemperature;
        public byte[] szRuleName = new byte[NAME_LEN];
        public byte[] byRes1 = new byte[63];
        public byte byRuleCalibType;
        public NET_VCA_POINT struPoint;
        public NET_VCA_POLYGON struRegion;
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("byEnabled", "byRuleID", "wDistance", "fEmissivity", "byRes", "byReflectiveEnabled", "fReflectiveTemperature",
                "szRuleName", "byRes1", "byRuleCalibType", "struPoint", "struRegion");
        }
    }


    public static class NET_DVR_THERMOMETRY_PRESETINFO extends Structure
    {
        public int   dwsize;
        public short wPresetNo;
        public byte[] byRes = new byte[2];
        public NET_DVR_THERMOMETRY_PRESETINFO_PARAM[] struPresetInfo = new NET_DVR_THERMOMETRY_PRESETINFO_PARAM[MAX_THERMOMETRY_REGION_NUM];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwsize", "wPresetNo", "byRes", "struPresetInfo");
        }
    }


    public static class NET_DVR_THERMAL_PIP extends Structure
    {
        public int   dwsize;
        public byte  byEnable;
        public byte  byPipMode;
        public byte  byOverlapType;
        public byte  byTransparency;
        public NET_VCA_POLYGON struPipRegion=new NET_VCA_POLYGON();
        public byte[] byRes = new byte[640];
        @Override
        protected List<String> getFieldOrder() {
            // TODO Auto-generated method stub
            return Arrays.asList("dwsize", "byEnable", "byPipMode", "byOverlapType", "byTransparency", "struPipRegion", "byRes");
        }
    }

    public static class NET_DVR_CAMERAPARAMCFG_EX extends Structure
    {
        public int dwSize;
        public NET_DVR_VIDEOEFFECT struVideoEffect=new NET_DVR_VIDEOEFFECT();   
        public NET_DVR_GAIN struGain=new NET_DVR_GAIN();
        public NET_DVR_WHITEBALANCE struWhiteBalance=new NET_DVR_WHITEBALANCE();
        public NET_DVR_EXPOSURE struExposure=new NET_DVR_EXPOSURE(); 
        public NET_DVR_GAMMACORRECT struGammaCorrect=new NET_DVR_GAMMACORRECT();
        public NET_DVR_WDR struWdr=new NET_DVR_WDR();
        public NET_DVR_DAYNIGHT struDayNight=new NET_DVR_DAYNIGHT();
        public NET_DVR_BACKLIGHT struBackLight=new NET_DVR_BACKLIGHT();
        public NET_DVR_NOISEREMOVE struNoiseRemove=new NET_DVR_NOISEREMOVE();
        public byte byPowerLineFrequencyMode; 
        public byte byIrisMode;
        public byte byMirror ;  
        public byte byDigitalZoom;  
        public byte byDeadPixelDetect;   
        public byte byBlackPwl;
        public byte byEptzGate;
        public byte byLocalOutputGate;
        public byte byCoderOutputMode;
        public byte byLineCoding; 
        public byte byDimmerMode; 
        public byte byPaletteMode; 
        public byte byEnhancedMode; 
        public byte byDynamicContrastEN;    
        public byte byDynamicContrast;   
        public byte byJPEGQuality;  
        public NET_DVR_CMOSMODECFG struCmosModeCfg=new NET_DVR_CMOSMODECFG();
        public byte byFilterSwitch; 
        public byte byFocusSpeed; 
        public byte byAutoCompensationInterval; 
        public byte bySceneMode; 
        public NET_DVR_DEFOGCFG struDefogCfg=new NET_DVR_DEFOGCFG();
        public NET_DVR_ELECTRONICSTABILIZATION struElectronicStabilization=new NET_DVR_ELECTRONICSTABILIZATION();
        public NET_DVR_CORRIDOR_MODE_CCD struCorridorMode=new NET_DVR_CORRIDOR_MODE_CCD();
        public byte   byExposureSegmentEnable; 
        public byte   byBrightCompensate;
        public byte   byCaptureModeN; 
        public byte   byCaptureModeP; 
        public NET_DVR_SMARTIR_PARAM struSmartIRParam=new NET_DVR_SMARTIR_PARAM(); 
        public NET_DVR_PIRIS_PARAM struPIrisParam=new NET_DVR_PIRIS_PARAM();
        public NET_DVR_LASER_PARAM_CFG struLaserParam=new NET_DVR_LASER_PARAM_CFG(); 
        public NET_DVR_FFC_PARAM  struFFCParam=new NET_DVR_FFC_PARAM();
        public NET_DVR_DDE_PARAM  struDDEParam=new NET_DVR_DDE_PARAM();
        public NET_DVR_AGC_PARAM  struAGCParam=new NET_DVR_AGC_PARAM();
        public byte   byLensDistortionCorrection;
        public byte byDistortionCorrectionLevel;
        public byte byCalibrationAccurateLevel;
        public byte byZoomedInDistantViewLevel;
        public NET_DVR_SNAP_CAMERAPARAMCFG struSnapCCD=new NET_DVR_SNAP_CAMERAPARAMCFG(); 
        public NET_DVR_OPTICAL_DEHAZE struOpticalDehaze=new NET_DVR_OPTICAL_DEHAZE();
        public NET_DVR_THERMOMETRY_AGC struThermAGC=new NET_DVR_THERMOMETRY_AGC();
        public byte   byFusionMode;
        public byte   byHorizontalFOV;
        public byte   byVerticalFOV;
        public byte   byBrightnessSuddenChangeSuppression;
        public byte   byGPSEnabled;
        public byte[]   byRes2 = new byte[155];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","struVideoEffect","struGain","struWhiteBalance","struExposure","struGammaCorrect","struWdr","struDayNight","struBackLight","struNoiseRemove","byPowerLineFrequencyMode","byIrisMode","byMirror","byDigitalZoom","byDeadPixelDetect","byBlackPwl","byEptzGate","byLocalOutputGate","byCoderOutputMode","byLineCoding","byDimmerMode","byPaletteMode","byEnhancedMode","byDynamicContrastEN","byDynamicContrast","byJPEGQuality","struCmosModeCfg","byFilterSwitch","byFocusSpeed","byAutoCompensationInterval","bySceneMode","struDefogCfg","struElectronicStabilization","struCorridorMode","byExposureSegmentEnable","byBrightCompensate","byCaptureModeN","byCaptureModeP","struSmartIRParam","struPIrisParam","struLaserParam","struFFCParam","struDDEParam","struAGCParam","byLensDistortionCorrection","byDistortionCorrectionLevel","byCalibrationAccurateLevel","byZoomedInDistantViewLevel","struSnapCCD","struOpticalDehaze","struThermAGC","byFusionMode","byHorizontalFOV","byVerticalFOV","byBrightnessSuddenChangeSuppression","byGPSEnabled","byRes2");
      }
    }
    
    public static class NET_DVR_GAIN extends Structure
    {
        public byte byGainLevel; 
        public byte byGainUserSet; 
        public byte[] byRes = new byte[2];
        public int dwMaxGainValue;

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byGainLevel","byGainUserSet","byRes","dwMaxGainValue");
      }
    }
    
    public static class NET_DVR_WHITEBALANCE extends Structure
    {
        public byte byWhiteBalanceMode; 
        public byte byWhiteBalanceModeRGain; 
        public byte byWhiteBalanceModeBGain; 
        public byte[] byRes = new byte[5];

		@Override
		protected List<String> getFieldOrder(){
            return Arrays.asList("byWhiteBalanceMode","byWhiteBalanceModeRGain","byWhiteBalanceModeBGain","byRes");
		}
    }

    public static class  NET_DVR_EXPOSURE extends Structure
    {
        public byte  byExposureMode; 
        public byte  byAutoApertureLevel;
        public byte[]  byRes = new byte[2];
        public int dwVideoExposureSet;    
        public int dwExposureUserSet; 
        public int dwRes;

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byExposureMode","byAutoApertureLevel","byRes","dwVideoExposureSet","dwExposureUserSet","dwRes");
      }
    }
    
    public static class  NET_DVR_GAMMACORRECT extends Structure
    {
        public byte byGammaCorrectionEnabled; 
        public byte byGammaCorrectionLevel;
        public byte[] byRes = new byte[6];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byGammaCorrectionEnabled","byGammaCorrectionLevel","byRes");
      }
    }
    
    public static class  NET_DVR_WDR extends Structure
    {
        public byte byWDREnabled; 
        public byte byWDRLevel1; 
        public byte byWDRLevel2; 
        public byte byWDRContrastLevel; 
        public byte[] byRes = new byte[16];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byWDREnabled","byWDRLevel1","byWDRLevel2","byWDRContrastLevel","byRes");
      }
    }
    
    public static class  NET_DVR_DAYNIGHT extends Structure
    {
        public byte byDayNightFilterType; 
        public byte bySwitchScheduleEnabled; 
        public byte byBeginTime; 
        public byte byEndTime; 
        public byte byDayToNightFilterLevel; 
        public byte byNightToDayFilterLevel; 
        public byte byDayNightFilterTime;
        public byte byBeginTimeMin; 
        public byte byBeginTimeSec; 
        public byte byEndTimeMin; 
        public byte byEndTimeSec;
        public byte byAlarmTrigState;

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byDayNightFilterType","bySwitchScheduleEnabled","byBeginTime","byEndTime","byDayToNightFilterLevel","byNightToDayFilterLevel","byDayNightFilterTime","byBeginTimeMin","byBeginTimeSec","byEndTimeMin","byEndTimeSec","byAlarmTrigState");
      }
    }

    public static class  NET_DVR_BACKLIGHT extends Structure
    {
        public byte byBacklightMode;
        public byte byBacklightLevel;
        public byte[] byRes1 = new byte[2];
        public int dwPositionX1; 
        public int dwPositionY1; 
        public int dwPositionX2; 
        public int dwPositionY2; 
        public byte[] byRes2 = new byte[4];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byBacklightMode","byBacklightLevel","byRes1","dwPositionX1","dwPositionY1","dwPositionX2","dwPositionY2","byRes2");
      }
    }
    
    public static class  NET_DVR_NOISEREMOVE extends Structure
    {
        public byte byDigitalNoiseRemoveEnable; 
        public byte byDigitalNoiseRemoveLevel; 
        public byte bySpectralLevel;      
        public byte byTemporalLevel;  
        public byte byDigitalNoiseRemove2DEnable;         
        public byte byDigitalNoiseRemove2DLevel;           
        public byte[] byRes = new byte[2];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byDigitalNoiseRemoveEnable","byDigitalNoiseRemoveLevel","bySpectralLevel","byTemporalLevel","byDigitalNoiseRemove2DEnable","byDigitalNoiseRemove2DLevel","byRes");
      }
    }
    
    public static class NET_DVR_CMOSMODECFG extends Structure
    {
        public byte byCaptureMod;  
        public byte byBrightnessGate;
        public byte byCaptureGain1;   
        public byte byCaptureGain2;   
        public int dwCaptureShutterSpeed1;
        public int dwCaptureShutterSpeed2;
        public byte[]  byRes = new byte[4];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byCaptureMod","byBrightnessGate","byCaptureGain1","byCaptureGain2","dwCaptureShutterSpeed1","dwCaptureShutterSpeed2","byRes");
      }
    }
    
    public static class NET_DVR_DEFOGCFG extends Structure
    {
        public byte byMode; 
        public byte byLevel; 
        public byte[] byRes = new byte[6];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byMode","byLevel","byRes");
      }
    }
    
    public static class NET_DVR_ELECTRONICSTABILIZATION extends Structure
    {
        public byte byEnable;
        public byte byLevel; 
        public byte[] byRes = new byte[6];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byEnable","byLevel","byRes");
      }
    }
    
    public static class NET_DVR_CORRIDOR_MODE_CCD extends Structure
    {
        public byte       byEnableCorridorMode; 
        public byte[]       byRes = new byte[11];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byEnableCorridorMode","byRes");
      }
    }
    
    public static class NET_DVR_SMARTIR_PARAM extends Structure
    {
        public byte  byMode;
        public byte  byIRDistance;
        public byte  byShortIRDistance;
        public byte  byLongIRDistance;

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byMode","byIRDistance","byShortIRDistance","byLongIRDistance");
      }
    }

    public static class NET_DVR_PIRIS_PARAM extends Structure
    {
        public byte  byMode;
        public byte  byPIrisAperture;
        public byte[]  byRes = new byte[6];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byMode","byPIrisAperture","byRes");
      }
    }

    public static class  NET_DVR_LASER_PARAM_CFG extends Structure
    {
        public byte    byControlMode;        
        public byte    bySensitivity;        
        public byte    byTriggerMode;        
        public byte    byBrightness;       
        public byte    byAngle;           
        public byte    byLimitBrightness;  
        public byte    byEnabled ;        
        public byte    byIllumination;     
        public byte    byLightAngle;       
        public byte[]    byRes = new byte[7];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byControlMode","bySensitivity","byTriggerMode","byBrightness","byAngle","byLimitBrightness","byEnabled","byIllumination","byLightAngle","byRes");
      }
    }
    
    public static class NET_DVR_FFC_PARAM extends Structure
    {
        public byte   byMode;
        public byte   byRes1;
        public short   wCompensateTime; 
        public byte[]   byRes2 = new byte[4];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byMode","byRes1","wCompensateTime","byRes2");
      }
    }
    
    public static class NET_DVR_DDE_PARAM extends Structure
    {
        public byte  byMode;
        public byte  byNormalLevel;
        public byte  byExpertLevel;
        public byte[]  byRes = new byte[5];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byMode","byNormalLevel","byExpertLevel","byRes");
      }
    }
    
    public static class NET_DVR_AGC_PARAM extends Structure
    {
        public byte  bySceneType;
        public byte  byLightLevel;
        public byte  byGainLevel; 
        public byte[]  byRes = new byte[5];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("bySceneType","byLightLevel","byGainLevel","byRes");
      }
    }
    
    public static class  NET_DVR_OPTICAL_DEHAZE extends Structure
    {
        public byte byEnable; 
        public byte[] byRes = new byte[7];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byEnable","byRes");
      }
    }

    public static class NET_DVR_THERMOMETRY_AGC extends Structure
    {
        public byte  byMode;
        public byte[]  byRes1 = new byte[3];
        public int     iHighTemperature;
        public int     iLowTemperature;
        public byte[]  byRes = new byte[8];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byMode","byRes1","iHighTemperature","iLowTemperature","byRes");
      }
    }
    
    public static class NET_DVR_MANUALSNAP extends Structure
    {
        public byte   byOSDEnable;
        public byte   byLaneNo;
        public byte   byChannel;
        public byte[]   byRes = new byte[21];
        @Override
        protected List<String> getFieldOrder(){
            return Arrays.asList("byOSDEnable","byLaneNo","byChannel","byRes");
        }
    }

    public static class NET_DVR_PIC_PARAM extends Structure
    {
        public Pointer pDVRFileName;
        public Pointer pSavedFileBuf;
        public int dwBufLen;
        public Pointer lpdwRetLen;
        public NET_DVR_ADDRESS struAddr = new NET_DVR_ADDRESS();
        public byte[] byRes = new byte[256];
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("pDVRFileName","pSavedFileBuf","dwBufLen","lpdwRetLen","struAddr","byRes");
        }
    }

    public static class NET_DVR_ADDRESS extends Structure
    {
        public NET_DVR_IPADDR struIP = new NET_DVR_IPADDR();
        public short wPort;
        public byte[] byRes = new byte[2];
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("struIP","wPort","byRes");
        }
    }

    public static class NET_DVR_FFC_MANUAL_INFO extends Structure
    {
        public int  dwSize;
        public int  dwChannel;
        public byte[]   byRes = new byte[64]; 
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","dwChannel","byRes");
        }
    }
	
    public static class  NET_DVR_SNAP_CAMERAPARAMCFG extends Structure
    {
        public byte byWDRMode;   
        public byte byWDRType;  
        public byte byWDRLevel;   
        public byte byRes1;
        public NET_DVR_TIME_EX struStartTime=new NET_DVR_TIME_EX(); 
        public NET_DVR_TIME_EX struEndTime=new NET_DVR_TIME_EX(); 
        public byte byDayNightBrightness; 
        public byte byMCEEnabled;
        public byte byMCELevel;
        public byte byAutoContrastEnabled;
        public byte byAutoContrastLevel;
        public byte byLSEDetailEnabled;
        public byte byLSEDetailLevel;
        public byte byLPDEEnabled;
        public byte byLPDELevel;
        public byte byLseEnabled; 
        public byte byLseLevel; 
        public byte byLSEHaloLevel;
        public byte byLseType; 
        public byte[] byRes2 = new byte[3];
        public NET_DVR_TIME_EX struLSEStartTime=new NET_DVR_TIME_EX(); 
        public NET_DVR_TIME_EX struLSEEndTime=new NET_DVR_TIME_EX(); 
        public byte byLightLevel;
        public byte byPlateContrastLevel;
        public byte byPlateSaturationLevel;
        public byte[] byRes = new byte[9];

		@Override
		protected List<String> getFieldOrder(){
        return Arrays.asList("byWDRMode","byWDRType","byWDRLevel","byRes1","struStartTime","struEndTime","byDayNightBrightness","byMCEEnabled","byMCELevel","byAutoContrastEnabled","byAutoContrastLevel","byLSEDetailEnabled","byLSEDetailLevel","byLPDEEnabled","byLPDELevel","byLseEnabled","byLseLevel","byLSEHaloLevel","byLseType","byRes2","struLSEStartTime","struLSEEndTime","byLightLevel","byPlateContrastLevel","byPlateSaturationLevel","byRes");
      }
    }
    
   
    
    public static class NET_DVR_ETHERNET_V30 extends Structure
    {
    	public NET_DVR_IPADDR[] struDVRIP=new NET_DVR_IPADDR[2];
        public NET_DVR_IPADDR struDVRIPMask=new NET_DVR_IPADDR();
        public int  dwNetInterface;
        public short  wDVRPort;
        public short  wMTU;
        public byte[]   byMACAddr = new byte[6];
        public byte byEthernetPortNo;
        public byte[]   byRes = new byte[1];
                
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("struDVRIP","struDVRIPMask","dwNetInterface","wDVRPort","wMTU","byMACAddr","byEthernetPortNo","byRes");
        }
    }
    
    public static class NET_DVR_PPPOECFG extends Structure
    {
        public int  dwPPPOE;
        public byte[]   sPPPoEUser = new byte[32];
        public byte[]   sPPPoEPassword = new byte[16];
        public NET_DVR_IPADDR struDVRIP=new NET_DVR_IPADDR();
                
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwPPPOE","sPPPoEUser","sPPPoEPassword","struDVRIP");
        }
    }
    
    public static class NET_DVR_NETCFG_V50 extends Structure
    {
        public int  dwSize;
        public NET_DVR_ETHERNET_V30[] struEtherNet=new NET_DVR_ETHERNET_V30[2];
        public NET_DVR_IPADDR[] struRes1=new NET_DVR_IPADDR[2];
        public NET_DVR_IPADDR struAlarmHostIpAddr=new NET_DVR_IPADDR();
        public byte[]   byRes2 = new byte[4];
        public short  wAlarmHostIpPort;
        public byte  byUseDhcp;
        public byte  byIPv6Mode;
        public NET_DVR_IPADDR struDnsServer1IpAddr=new NET_DVR_IPADDR();
        public NET_DVR_IPADDR struDnsServer2IpAddr=new NET_DVR_IPADDR();
        public byte[]   byIpResolver = new byte[64];
        public short  wIpResolverPort;
        public short  wHttpPortNo;
        public NET_DVR_IPADDR struMulticastIpAddr=new NET_DVR_IPADDR();
        public NET_DVR_IPADDR struGatewayIpAddr=new NET_DVR_IPADDR();
        public NET_DVR_PPPOECFG struPPPoE=new NET_DVR_PPPOECFG();
        public byte  byEnablePrivateMulticastDiscovery;
        public byte  byEnableOnvifMulticastDiscovery;
        public short  wAlarmHost2IpPort;
        public NET_DVR_IPADDR struAlarmHost2IpAddr=new NET_DVR_IPADDR();
        public byte  byEnableDNS;
        public byte  byAlarmOverTLS;
        public byte[]   byRes = new byte[598];
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","struEtherNet","struRes1","struAlarmHostIpAddr","byRes2","wAlarmHostIpPort",
        		"byUseDhcp","byIPv6Mode","struDnsServer1IpAddr","struDnsServer2IpAddr","byIpResolver","wIpResolverPort",
        		"wHttpPortNo","struMulticastIpAddr","struGatewayIpAddr","struPPPoE","byEnablePrivateMulticastDiscovery",
        		"byEnableOnvifMulticastDiscovery","wAlarmHost2IpPort","struAlarmHost2IpAddr","byEnableDNS","byAlarmOverTLS",
        		"byRes");
        }
    }
    
    public static class NET_DVR_EVENT_CARD_LINKAGE_COND extends Structure
    {
        public int  dwSize;
        public int  dwEventID;
        public short  wLocalControllerID;
        public byte[]   byRes = new byte[106];
                
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","dwEventID","wLocalControllerID","byRes");
        }
    }
    
    public static class NET_DVR_EVENT_LINKAGE_INFO extends Structure
    {
        public short  wMainEventType;
        public short  wSubEventType;
        public byte[]   byRes = new byte[28];
                
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("wMainEventType","wSubEventType","byRes");
        }
    }
    
    public static class NET_DVR_EVETN_CARD_LINKAGE_UNION extends Union
	{
		public byte[] byCardNo = new byte[32];
		public NET_DVR_EVENT_LINKAGE_INFO struEventLinkage = new NET_DVR_EVENT_LINKAGE_INFO();
		public byte[] byMACAddr = new byte[6];
		public byte[] byEmployeeNo = new byte[32];
	}    
    
    public static class NET_DVR_EVENT_CARD_LINKAGE_CFG_V51 extends Structure
    {
        public int  dwSize;
        public byte  byProMode;
        public byte[]   byRes1 = new byte[3];
        public int  dwEventSourceID;
        public NET_DVR_EVETN_CARD_LINKAGE_UNION uLinkageInfo=new NET_DVR_EVETN_CARD_LINKAGE_UNION();
        public byte[]   byAlarmout = new byte[512];
        public byte[]   byRes2 = new byte[32];
        public byte[]   byOpenDoor = new byte[256];
        public byte[]   byCloseDoor = new byte[256];
        public byte[]   byNormalOpen = new byte[256];
        public byte[]   byNormalClose = new byte[256];
        public byte  byMainDevBuzzer;
        public byte  byCapturePic;
        public byte  byRecordVideo;
        public byte  byMainDevStopBuzzer;
        public byte[]   byRes3 = new byte[28];
        public byte[]   byReaderBuzzer = new byte[512];
        public byte[]   byAlarmOutClose = new byte[512];
        public byte[]   byAlarmInSetup = new byte[512];
        public byte[]   byAlarmInClose = new byte[512];
        public byte[]   byReaderStopBuzzer = new byte[512];
        public byte[]   byRes = new byte[512];
        
                
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","byProMode","byRes1","dwEventSourceID",
        		"uLinkageInfo","byAlarmout","byRes2","byOpenDoor",
        		"byCloseDoor","byNormalOpen","byNormalClose","byMainDevBuzzer",
        		"byCapturePic","byRecordVideo","byMainDevStopBuzzer","byRes3",
        		"byReaderBuzzer","byAlarmOutClose","byAlarmInSetup","byAlarmInClose",
        		"byReaderStopBuzzer","byRes");
        }
    }
    
    public static class NET_DVR_MATRIX_ABILITY extends Structure
    {
        public int  dwSize;
        public byte  byDecNums;
        public byte  byStartChan;
        public byte  byVGANums;
        public byte  byBNCNums;       
        public byte[][]   byVGAWindowMode = new byte[8][12];
        public byte[]   byBNCWindowMode = new byte[4];
        public byte  byDspNums;
        public byte  byHDMINums;
        public byte  byDVINums;
        public byte[]   byRes1 = new byte[13];
        public byte[]   bySupportResolution = new byte[64];
        public byte[][]   byHDMIWindowMode = new byte[4][8];
        public byte[][]   byDVIWindowMode = new byte[4][8];
        public byte[]   byRes2 = new byte[24];   
                
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","byDecNums","byStartChan","byVGANums",
        		"byBNCNums","byVGAWindowMode","byBNCWindowMode","byDspNums",
        		"byHDMINums","byDVINums","byRes1","bySupportResolution",
        		"byHDMIWindowMode","byDVIWindowMode","byRes2");
        }
    }
    
    public static class NET_DVR_CARD_PASSWD_CFG extends Structure
    {
        public int  dwSize;
        public byte[]   byCardNo = new byte[32];
        public byte[]   byCardPassword = new byte[8];
        public int  dwErrorCode;
        public byte  byCardValid;
        public byte[]   byRes2 = new byte[23];   
                
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","byCardNo","byCardPassword","dwErrorCode",
        		"byCardValid","byRes2");
        }
    }
    

    public static class NET_DVR_ACS_EVENT_DETAIL extends Structure {
		public int dwSize;
		public byte byCardNo[] = new byte[32];
		public byte byCardType;
		public byte byWhiteListNo;
		public byte byReportChannel;
		public byte byCardReaderKind;
		public int dwCardReaderNo;
		public int dwDoorNo;
		public int dwVerifyNo;
		public int dwAlarmInNo;
		public int dwAlarmOutNo;
		public int dwCaseSensorNo;
		public int dwRs485No;
		public int dwMultiCardGroupNo;
		public short wAccessChannel;
		public byte byDeviceNo;
		public byte byDistractControlNo;
		public int dwEmployeeNo;
		public short wLocalControllerID;
		public byte byInternetAccess;
		public byte byType;
		public byte byMACAddr[] = new byte[6];
		public byte bySwipeCardType;
		public byte byRes2;
		public int dwSerialNo;
		public byte byChannelControllerID;
		public byte byChannelControllerLampID;
		public byte byChannelControllerIRAdaptorID;
		public byte byChannelControllerIREmitterID;
		public byte byRes[] = new byte[108];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwSize", "byCardNo", "byCardType",
					"byWhiteListNo", "byReportChannel", "byCardReaderKind",
					"dwCardReaderNo", "dwDoorNo", "dwVerifyNo", "dwAlarmInNo",
					"dwAlarmOutNo", "dwCaseSensorNo", "dwRs485No",
					"dwMultiCardGroupNo", "wAccessChannel", "byDeviceNo",
					"byDistractControlNo", "dwEmployeeNo",
					"wLocalControllerID", "byInternetAccess", "byType",
					"byMACAddr", "bySwipeCardType", "byRes2", "dwSerialNo",
					"byChannelControllerID", "byChannelControllerLampID",
					"byChannelControllerIRAdaptorID",
					"byChannelControllerIREmitterID", "byRes");
		}
	}
	
    
	public static class NET_DVR_ACS_EVENT_CFG extends Structure {
		public int dwSize;
		public int dwMajor;
		public int dwMinor;
		public NET_DVR_TIME struTime;
		public byte sNetUser[] = new byte[16];
		public NET_DVR_IPADDR struRemoteHostAddr;
		public NET_DVR_ACS_EVENT_DETAIL struAcsEventInfo;
		public int dwPicDataLen;
		public Pointer pPicData;
		public byte byRes[] = new byte[64];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwSize", "dwMajor", "dwMinor", "struTime",
					"sNetUser", "struRemoteHostAddr", "struAcsEventInfo",
					"dwPicDataLen", "pPicData", "byRes");
		}
		public NET_DVR_ACS_EVENT_CFG(Pointer p) {
			super(p);
		}
	}
    public static class NET_DVR_CAPTURE_FACE_CFG extends Structure
    {
        public int  dwSize;
        public int  dwFaceTemplate1Size;         
		public ByteByReference pFaceTemplate1Buffer;
		public int  dwFaceTemplate2Size;
		public ByteByReference pFaceTemplate2Buffer;
		public int  dwFacePicSize;
		public ByteByReference pFacePicBuffer;
		public byte byFaceQuality1;
		public byte byFaceQuality2;
		public byte byCaptureProgress;
		public byte byRes1;
		public int  dwInfraredFacePicSize;
		public ByteByReference pInfraredFacePicBuffer;		
		public byte[]   byRes = new byte[116];  
		
		public NET_DVR_CAPTURE_FACE_CFG(Pointer p){
			super(p);
		}        
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","dwFaceTemplate1Size","pFaceTemplate1Buffer","dwFaceTemplate2Size",
        		"pFaceTemplate2Buffer","dwFacePicSize","pFacePicBuffer","byFaceQuality1",
        		"byFaceQuality2","byCaptureProgress","byRes1","dwInfraredFacePicSize",
        		"pInfraredFacePicBuffer","byRes");
        }
    }
    
    //add by jiaoyy//
    public static class NET_DVR_FACE_PARAM_CFG extends Structure {
		public int dwSize;
		public byte byCardNo[] = new byte[32];
		public int dwFaceLen;
		public Pointer pFaceBuffer;
		public byte byEnableCardReader[] = new byte[512];
		public byte byFaceID;
		public byte byFaceDataType;
		public byte byRes[] = new byte[126];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwSize", "byCardNo", "dwFaceLen",
					"pFaceBuffer", "byEnableCardReader", "byFaceID",
					"byFaceDataType", "byRes");
		}
		public NET_DVR_FACE_PARAM_CFG(Pointer p) {
			super(p);
		}
	}
    
    /*add by jiaoyy*/
    public static class NET_DVR_CARD_CFG_SEND_DATA extends Structure {
		public int dwSize;
		public byte byCardNo[] = new byte[32];
		public int dwCardUserId;
		public byte byRes[] = new byte[12];

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwSize", "byCardNo", "dwCardUserId", "byRes");
		}
//		public NET_DVR_CARD_CFG_SEND_DATA(Pointer p) {
//			super(p);
//		}
	}
    public static class NET_DVR_ACS_EVENT_INFO extends Structure
    {
    	public int dwSize;
    	public byte[] byCardNo = new byte[ACS_CARD_NO_LEN]; 
    	public byte byCardType;
    	public byte byWhiteListNo; 
    	public byte byReportChannel; 
    	public byte byCardReaderKind; 
        public int dwCardReaderNo; 
        public int dwDoorNo; 
        public int dwVerifyNo; 
        public int dwAlarmInNo;
        public int dwAlarmOutNo; 
        public int dwCaseSensorNo; 
        public int dwRs485No;   
        public int dwMultiCardGroupNo; 
        public short wAccessChannel;  
        public byte  byDeviceNo;   
        public byte  byDistractControlNo;
        public int dwEmployeeNo; 
        public short wLocalControllerID; 
        public byte  byInternetAccess; 
        public byte    byType; 
        public byte[]  byMACAddr = new byte[MACADDR_LEN];
        public byte  bySwipeCardType;
        public byte  byMask;
        public int dwSerialNo; 
        public byte  byChannelControllerID; 
        public byte  byChannelControllerLampID; 
        public byte  byChannelControllerIRAdaptorID; 
        public byte  byChannelControllerIREmitterID; 
        public byte[]  byRes=new byte[4];
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","byCardNo", "byCardType","byWhiteListNo",
        		"byReportChannel","byCardReaderKind", "dwCardReaderNo","dwDoorNo",
        		"dwVerifyNo","dwAlarmInNo", "dwAlarmOutNo","dwCaseSensorNo",
        		"dwRs485No","dwMultiCardGroupNo", "wAccessChannel","byDeviceNo",
        		"byDistractControlNo","dwEmployeeNo", "wLocalControllerID","byInternetAccess",
        		"byType","byMACAddr", "bySwipeCardType","byMask",
        		"dwSerialNo","byChannelControllerID", "byChannelControllerLampID","byChannelControllerIRAdaptorID",
        		"byChannelControllerIREmitterID","byRes");
        }
    }
    
    public static class NET_DVR_ACS_EVENT_INFO_EXTEND extends Structure
	{
		public int dwFrontSerialNo; //事件流水号，为0无效（若该字段为0，平台根据dwSerialNo判断是否丢失事件；若该字段不为0，平台根据该字段和dwSerialNo字段共同判断是否丢失事件）（主要用于解决报警订阅后导致dwSerialNo不连续的情况）
		public byte  byUserType; //人员类型：0-无效，1-普通人（主人），2-来宾（访客），3-黑名单人，4-管理员
		public byte  byCurrentVerifyMode; //读卡器当前验证方式：0-无效，1-休眠，2-刷卡+密码，3-刷卡，4-刷卡或密码，5-指纹，6-指纹+密码，7-指纹或刷卡，8-指纹+刷卡，9-指纹+刷卡+密码，10-人脸或指纹或刷卡或密码，11-人脸+指纹，12-人脸+密码，13-人脸+刷卡，14-人脸，15-工号+密码，16-指纹或密码，17-工号+指纹，18-工号+指纹+密码，19-人脸+指纹+刷卡，20-人脸+密码+指纹，21-工号+人脸，22-人脸或人脸+刷卡，23-指纹或人脸，24-刷卡或人脸或密码，25-刷卡或人脸，26-刷卡或人脸或指纹，27-刷卡或指纹或密码
		public byte  byCurrentEvent; //是否为实时事件：0-无效，1-是（实时事件），2-否（离线事件）
		public byte  byPurePwdVerifyEnable; //设备是否支持纯密码认证， 0-不支持，1-支持
		public byte[]  byEmployeeNo = new byte[NET_SDK_EMPLOYEE_NO_LEN]; //工号（人员ID）（对于设备来说，如果使用了工号（人员ID）字段，byEmployeeNo一定要传递，如果byEmployeeNo可转换为dwEmployeeNo，那么该字段也要传递；对于上层平台或客户端来说，优先解析byEmployeeNo字段，如该字段为空，再考虑解析dwEmployeeNo字段）
		public byte  byAttendanceStatus; //考勤状态：0-未定义,1-上班，2-下班，3-开始休息，4-结束休息，5-开始加班，6-结束加班
		public byte  byStatusValue; //考勤状态值
		public byte[]  byRes2 = new byte[2];
		public byte[]  byUUID = new byte[NET_SDK_UUID_LEN]; //UUID（该字段仅在对接萤石平台过程中才会使用）
		public byte[]  byDeviceName = new byte[NET_DEV_NAME_LEN];   //设备序列号
		public byte[]  byRes = new byte[24];

		public NET_DVR_ACS_EVENT_INFO_EXTEND(Pointer p){
			super(p);
		}

		@Override
		protected List<String> getFieldOrder(){
			return Arrays.asList("dwFrontSerialNo","byUserType","byCurrentVerifyMode","byCurrentEvent",
					"byPurePwdVerifyEnable","byEmployeeNo","byAttendanceStatus","byStatusValue",
					"byRes2", "byUUID", "byDeviceName","byRes");
		}
	}

	public static class NET_DVR_ACS_EVENT_INFO_EXTEND_V20 extends Structure
	{
		public byte byRemoteCheck; //是否需要远程核验（0-无效，1-不需要（默认），2-需要）
		public byte byThermometryUnit; //测温单位（0-摄氏度（默认），1-华氏度，2-开尔文）
		public byte byIsAbnomalTemperature; //人脸抓拍测温是否温度异常：1-是，0-否
		public byte byRes2;
		public float fCurrTemperature; //人脸温度（精确到小数点后一位）
		public NET_VCA_POINT struRegionCoordinates; //人脸温度坐标
		public int dwQRCodeInfoLen; //二维码信息长度，不为0是表示后面带数据
		public int dwVisibleLightDataLen; //热成像相机可见光图片长度，不为0是表示后面带数据
		public int dwThermalDataLen; //热成像图片长度，不为0是表示后面带数据
		public ByteByReference pQRCodeInfo; //二维码信息指针
		public ByteByReference pVisibleLightData; //热成像相机可见光图片指针
		public ByteByReference pThermalData; //热成像图片指针
		public byte[]  byAttendanceLabel = new byte[64]; //考勤自定义名称
		public byte[]  byRes = new byte[960];

		public NET_DVR_ACS_EVENT_INFO_EXTEND_V20(Pointer p){
			super(p);
		}

		@Override
		protected List<String> getFieldOrder(){
			return Arrays.asList("byRemoteCheck","byThermometryUnit","byIsAbnomalTemperature","byRes2",
					"fCurrTemperature","struRegionCoordinates","dwQRCodeInfoLen","dwVisibleLightDataLen",
					"dwThermalDataLen","pQRCodeInfo","pVisibleLightData", "pThermalData", "byAttendanceLabel","byRes");
		}
	}
    
    public static class NET_DVR_ACS_ALARM_INFO extends Structure
    {
        public int  dwSize;
        public int  dwMajor;
        public int  dwMinor;
        public NET_DVR_TIME struTime=new NET_DVR_TIME();
        public byte[]   sNetUser = new byte[16];
        public NET_DVR_IPADDR struRemoteHostAddr=new NET_DVR_IPADDR();
        public NET_DVR_ACS_EVENT_INFO struAcsEventInfo=new NET_DVR_ACS_EVENT_INFO();
        public int  dwPicDataLen;
        public ByteByReference pPicData;	
        public short  wInductiveEventType;
        public byte  byPicTransType;
        public byte  byRes1;
        public int  dwIOTChannelNo;
        public ByteByReference pAcsEventInfoExtend;	
        public byte  byAcsEventInfoExtend;
        public byte  byTimeType;
        public byte  byRes2;
		public byte  byAcsEventInfoExtendV20;
		public ByteByReference pAcsEventInfoExtendV20;
		public byte[]   byRes = new byte[4];
              
		public NET_DVR_ACS_ALARM_INFO(Pointer p){
			super(p);
		}
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","dwMajor", "dwMinor","struTime",
        		"sNetUser","struRemoteHostAddr", "struAcsEventInfo","dwPicDataLen",
        		"pPicData","wInductiveEventType", "byPicTransType","byRes1",
        		"dwIOTChannelNo","pAcsEventInfoExtend", "byAcsEventInfoExtend","byTimeType", 
        		"byRes2","byAcsEventInfoExtendV20", "pAcsEventInfoExtendV20", "byRes");
        }
    }
    
    public static class NET_DVR_PASSNUM_INFO_ALARM extends Structure
    {
    	public int  dwSize; 
    	public int  dwAccessChannel; 
    	public NET_DVR_TIME_V30  struSwipeTime=new NET_DVR_TIME_V30(); 
        public byte[]    byNetUser=new byte[16] ;
        public NET_DVR_IPADDR    struRemoteHostAddr=new NET_DVR_IPADDR();
        public int    dwEntryTimes; 
        public int    dwExitTimes; 
        public int    dwTotalTimes;
        public byte[] byRes=new byte[300];
        
        @Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","dwAccessChannel", "struSwipeTime","byNetUser",
        		"struRemoteHostAddr","dwEntryTimes", "dwExitTimes","dwTotalTimes",
        		"byRes");
        }
    }
	
	public static class NET_DVR_JPEGPICTURE_WITH_APPENDDATA extends Structure
	{
		public int   dwSize;
		public int   dwChannel;
		public int   dwJpegPicLen;
		public Pointer pJpegPicBuff;
		public int   dwJpegPicWidth;
		public int   dwJpegPicHeight; 
		public int   dwP2PDataLen;
		public Pointer pP2PDataBuff; 
		public byte byIsFreezedata;
		public byte[] byRes1 = new byte[3];
		public int   dwVisiblePicLen;
		public Pointer pVisiblePicBuff;

		public NET_VCA_RECT struThermalValidRect=new NET_VCA_RECT();
		public NET_VCA_RECT struVisibleValidRect=new NET_VCA_RECT();
		public byte[] byRes = new byte[208];
		
		@Override
        protected List<String> getFieldOrder(){
        return Arrays.asList("dwSize","dwChannel","dwJpegPicLen","pJpegPicBuff",
        		"dwJpegPicWidth","dwJpegPicHeight","dwP2PDataLen","pP2PDataBuff",
				"byIsFreezedata","byRes1","dwVisiblePicLen","pVisiblePicBuff",
				"struThermalValidRect","struVisibleValidRect","byRes");
        }
	}

	public static class NET_DVR_ALARM_ISAPI_INFO extends Structure
	{
		public Pointer pAlarmData;    // 报警数据
		public int dwAlarmDataLen;    // 报警数据长度
		public byte byDataType;        // 0-invalid,1-xml,2-json
		public byte byPicturesNumber;  // 图片数量
		public byte[] byRes = new byte[2];
		public Pointer pPicPackData;         // 图片变长部分
		public byte[] byRes1 = new byte[32];

		public NET_DVR_ALARM_ISAPI_INFO(Pointer p){
			super(p);
		}

		@Override
		protected List<String> getFieldOrder(){
			return Arrays.asList("pAlarmData","dwAlarmDataLen","byDataType","byPicturesNumber","byRes","pPicPackData","byRes1");
		}
	}

	public static class NET_DVR_ALARM_ISAPI_PICDATA extends Structure
	{
    	public int dwPicLen;
    	public byte byPicType;
		public byte[] byRes = new byte[3];
		public byte[] szFilename = new byte[256] ;
		public Pointer pPicData;

		@Override
		protected List<String> getFieldOrder() {
			return Arrays.asList("dwPicLen","byPicType","byRes","szFilename","pPicData");
		}
	}

	public static class NET_DVR_SETUPALARM_PARAM_V50 extends Structure
	{
		public int   dwSize;
		public byte  byLevel; //布防优先级，0-一等级（高），1-二等级（中），2-三等级（低）
		public byte  byAlarmInfoType; //上传报警信息类型（抓拍机支持），0-老报警信息（NET_DVR_PLATE_RESULT），1-新报警信息(NET_ITS_PLATE_RESULT)2012-9-28
		public byte  byRetAlarmTypeV40; //0--返回NET_DVR_ALARMINFO_V30或NET_DVR_ALARMINFO, 1--设备支持NET_DVR_ALARMINFO_V40则返回NET_DVR_ALARMINFO_V40，不支持则返回NET_DVR_ALARMINFO_V30或NET_DVR_ALARMINFO
		public byte  byRetDevInfoVersion; //CVR上传报警信息回调结构体版本号 0-COMM_ALARM_DEVICE， 1-COMM_ALARM_DEVICE_V40
		public byte  byRetVQDAlarmType; //VQD报警上传类型，0-上传报报警NET_DVR_VQD_DIAGNOSE_INFO，1-上传报警NET_DVR_VQD_ALARM
		//1-表示人脸侦测报警扩展(INTER_FACE_DETECTION),0-表示原先支持结构(INTER_FACESNAP_RESULT)
		public byte  byFaceAlarmDetection;
		//Bit0- 表示二级布防是否上传图片: 0-上传，1-不上传
		//Bit1- 表示开启数据上传确认机制；0-不开启，1-开启
		//Bit6- 表示雷达检测报警(eventType:radarDetection)是否开启实时上传；0-不开启，1-开启（用于web插件实时显示雷达目标轨迹）
		public byte  bySupport;
		//断网续传类型
		//bit0-车牌检测（IPC） （0-不续传，1-续传）
		//bit1-客流统计（IPC）  （0-不续传，1-续传）
		//bit2-热度图统计（IPC） （0-不续传，1-续传）
		//bit3-人脸抓拍（IPC） （0-不续传，1-续传）
		//bit4-人脸对比（IPC） （0-不续传，1-续传）
		//bit5-JSON报警透传（IPC） （0-不续传，1-续传）
		//bit6-热度图按人员停留时间统计数据上传事件（0-不续传，1-续传）
		//bit7-热度图按人数统计数据上传事件的确认机制（0-不续传，1-续传）
		public byte  byBrokenNetHttp;
		public short  wTaskNo;    //任务处理号 和 (上传数据NET_DVR_VEHICLE_RECOG_RESULT中的字段dwTaskNo对应 同时 下发任务结构 NET_DVR_VEHICLE_RECOG_COND中的字段dwTaskNo对应)
		public byte  byDeployType;    //布防类型：0-客户端布防，1-实时布防
		public byte  bySubScription;	//订阅，按位表示，未开启订阅不上报  //占位
		//Bit7-移动侦测人车分类是否传图；0-不传图(V30上报)，1-传图(V40上报)
		public byte[]  byRes1 = new byte[2];
		public byte  byAlarmTypeURL;//bit0-表示人脸抓拍报警上传（INTER_FACESNAP_RESULT）；0-表示二进制传输，1-表示URL传输（设备支持的情况下，设备支持能力根据具体报警能力集判断,同时设备需要支持URL的相关服务，当前是”云存储“）
		//bit1-表示EVENT_JSON中图片数据长传类型；0-表示二进制传输，1-表示URL传输（设备支持的情况下，设备支持能力根据具体报警能力集判断）
		//bit2 - 人脸比对(报警类型为COMM_SNAP_MATCH_ALARM)中图片数据上传类型：0 - 二进制传输，1 - URL传输
		//bit3 - 行为分析(报警类型为COMM_ALARM_RULE)中图片数据上传类型：0 - 二进制传输，1 - URL传输，本字段设备是否支持，对应软硬件能力集中<isSupportBehaviorUploadByCloudStorageURL>节点是否返回且为true
		public byte  byCustomCtrl;//Bit0- 表示支持副驾驶人脸子图上传: 0-不上传,1-上传
		public byte[]  byRes4 = new byte[128];

		@Override
		protected List<String> getFieldOrder(){
			return Arrays.asList("dwSize","byLevel","byAlarmInfoType","byRetAlarmTypeV40",
					"byRetDevInfoVersion","byRetVQDAlarmType","byFaceAlarmDetection","bySupport",
					"byBrokenNetHttp","wTaskNo","byDeployType","bySubScription",
					"byRes1","byAlarmTypeURL","byCustomCtrl","byRes4");
		}
	}

	public static class NET_DVR_RECORDSCHED extends Structure
	{
		public NET_DVR_SCHEDTIME struRecordTime = new NET_DVR_SCHEDTIME();
		public byte byRecordType; //0:定时录像，1:移动侦测，2:报警录像，3:动测|报警，4:动测&报警, 5:命令触发, 6: 智能录像,10-PIR报警，11-无线报警，12-呼救报警，13-所有报警,
		// 14-智能交通事件，15越界侦测，16区域入侵侦测，17音频异常侦测
		public byte[] reservedData = new byte[3];
		@Override
		protected List<String> getFieldOrder(){
			return Arrays.asList("struRecordTime","byRecordType","reservedData");
		}
	}

	public static class NET_DVR_RECORDDAY extends Structure
	{
		public short wAllDayRecord;/* 是否全天录像 0-否 1-是*/
		public byte byRecordType;/* 录象类型 0:定时录像，1:移动侦测，2:报警录像，3:动测|报警，4:动测&报警 5:命令触发, 6: 智能录像,
                                                                    10-PIR报警，11-无线报警，12-呼救报警，13-移动|报警输入|PIR|无线报警|呼救报警,14-智能交通事件,15-越界侦测,16-区域入侵,17-声音异常,
                                    18-场景变更侦测,19-智能侦测（越界侦测|区域入侵|进入区域|离开区域|人脸识别）,20－人脸侦测, 21-POS录像*/
		public byte reservedData;
		@Override
		protected List<String> getFieldOrder(){
			return Arrays.asList("wAllDayRecord","byRecordType","reservedData");
		}
	}

	public static class NET_DVR_RECORD_V30 extends Structure
	{
		public int dwSize;
		public int dwRecord;//是否录像 0-否  1-是
		public NET_DVR_RECORDDAY[] struRecAllDay = (NET_DVR_RECORDDAY[]) new NET_DVR_RECORDDAY().toArray(7);
		public byte[] chRecordSched = new byte[448];
		public int dwRecordTime;/* 录象延时长度 0-5秒， 1-10秒， 2-30秒， 3-1分钟， 4-2分钟， 5-5分钟， 6-10分钟*/
		public int dwPreRecordTime;/* 预录时间 0-不预录 1-5秒 2-10秒 3-15秒 4-20秒 5-25秒 6-30秒 7-0xffffffff(尽可能预录) */
		public int dwRecorderDuration;/* 录像保存的最长时间 */
		public byte byRedundancyRec;/*是否冗余录像,重要数据双备份：0/1*/
		public byte byAudioRec;/*录像时复合流编码时是否记录音频数据：国外有此法规*/
		public byte byStreamType;// 0-主码流，1-子码流，2-主子码流同时 3-三码流
		public byte byPassbackRecord;// 0:不回传录像 1：回传录像
		public short wLockDuration;// 录像锁定时长，单位小时 0表示不锁定，0xffff表示永久锁定，录像段的时长大于锁定的持续时长的录像，将不会锁定
		public byte byRecordBackup;// 0:录像不存档 1：录像存档
		public byte bySVCLevel;//SVC抽帧类型：0-不抽，1-抽二分之一 2-抽四分之三
		public byte[] byReserve = new byte[4];

		@Override
		protected List<String> getFieldOrder(){
			return Arrays.asList("dwSize","dwRecord","struRecAllDay","chRecordSched",
					"dwRecordTime","dwPreRecordTime","dwRecorderDuration","byRedundancyRec",
					"byAudioRec","byStreamType","byPassbackRecord","wLockDuration",
					"byRecordBackup","bySVCLevel","byReserve");
		}
	}



	boolean NET_DVR_Init();
	boolean NET_DVR_Cleanup();
	int NET_DVR_GetSDKVersion();
	int NET_DVR_GetLastError();
	int NET_DVR_Login_V40(Pointer pLoginInfo, Pointer lpDevice);
	boolean NET_DVR_RestoreConfig(int lUserID);
	boolean NET_DVR_ControlGateway(int lUserID, int lGatewayIndex, int dwStaic);
	boolean NET_DVR_RemoteControl(int lUserID, int dwCommand, Pointer lpInBuffer, int dwInBufferSize);
	boolean NET_DVR_Logout(int lUserID);
	boolean NET_DVR_STDXMLConfig(int lUserID, NET_DVR_XML_CONFIG_INPUT lpInputParam, NET_DVR_XML_CONFIG_OUTPUT lpOutputParam);
	boolean NET_DVR_GetDVRConfig(int lUserID, int dwCommand, int lChannel, Pointer lpOutBuffer, int dwOutBufferSize, IntByReference lpBytesReturned);
	boolean NET_DVR_SetDVRConfig(int lUserID, int dwCommand, int lChannel, Pointer lpInBuffer, int dwInBufferSize);
	boolean NET_DVR_GetDeviceConfig(int lUserID, int dwCommand, int dwCount, Pointer lpInBuffer, int dwInBufferSize, Pointer lpStatusList, Pointer lpOutBuffer, int dwOutBuggerSize);
	boolean NET_DVR_SetDeviceConfig(int lUserID, int dwCommand, int dwCount, Pointer lpInBuffer, int dwInBufferSize, Pointer lpStatusList, Pointer lpInParamBuffer, int dwInParamBufferSize);
	boolean NET_DVR_PTZControlWithSpeed_Other(int lUserID, int lChannel, int dwPTZCommand, int dwStop, int dwSpeed);
	boolean NET_DVR_ClientSetVideoEffect(int lRealHandle,int dwBrightValue,int dwContrastValue, int dwSaturationValue,int dwHueValue);
	boolean NET_DVR_ClientGetVideoEffect(int lRealHandle,Pointer pBrightValue,Pointer pContrastValue, Pointer pSaturationValue,Pointer pHueValue);

	boolean NET_DVR_GetSTDConfig(int lUserID, int dwCommand, Pointer lpConfigParam);
	boolean NET_DVR_SetSTDConfig(int lUserID, int dwCommand, Pointer lpConfigParam);
	boolean NET_DVR_GetSTDAbility(int lUserID, int dwAbilityType, Pointer lpAbilityParam);
	boolean NET_DVR_SetDVRMessageCallBack_V30(FMSGCallBack fMessageCallBack, Pointer pUser);
	boolean NET_DVR_SetDVRMessageCallBack_V50(int iIndex, FMSGCallBack fMessageCallBack, Pointer pUser);
	int NET_DVR_SetupAlarmChan_V30(int lUserID);
	int NET_DVR_SetupAlarmChan_V41(int lUserID, Pointer lpSetupParam);
	int NET_DVR_SetupAlarmChan_V50(int lUserID, Pointer lpSetupParam, Pointer pData, int dwDataLen);
	boolean NET_DVR_CloseAlarmChan_V30(int lAlarmHandle);
	int NET_DVR_StartRemoteConfig(int lUserID, int dwCommand, Pointer lpInBuffer, int dwInBufferLen, fRemoteConfigCallback cbStateCallback, Pointer pUserData);
	boolean NET_DVR_SendRemoteConfig(int lHandle, int dwDataType, Pointer pSendBuf, int dwBufSize);
	boolean NET_DVR_StopRemoteConfig(int lHandle);
	int NET_DVR_MatrixStartPassiveDecode(int lUserID, int dwDecChanNum, Pointer lpPassiveMode);
	boolean NET_DVR_MatrixSendData(int lPassiveHandle, Pointer pSendBuf, int dwBufSize);
	boolean NET_DVR_MatrixStopPassiveDecode(int lPassiveHandle);
	int NET_DVR_MatrixGetPassiveDecodeStatus(int lPassiveHandle);
	boolean NET_DVR_MatrixGetDecChanCfg(int lUserID, int dwDecChan, Pointer lpInter);
	boolean NET_DVR_MatrixSetDecChanCfg(int lUserID, int dwDecChan, Pointer lpInter);
	int NET_DVR_UploadFile_V40(int lUserID, int dwUploadType, Pointer lpInBuffer, int dwInBufferLen, String sFileName, Pointer lpOutBuffer, int dwOutBufferLen);
	int NET_DVR_UploadSend(int lUploadHandle, Pointer pstruSendParamIN, Pointer lpOutBuffer);
	int NET_DVR_GetUploadState(int lUploadHandle, IntByReference pProgress);
	boolean NET_DVR_GetUploadResult(int lUploadHandle, Pointer lpOutBuffer, int dwOutBufferSize);
	boolean NET_DVR_UploadClose(int lUploadHandle);
	boolean NET_DVR_SetAlarmHostOut(int lUserID,int lAlarmOutPort,int lAlarmOutStatic);

	int NET_DVR_CreateOpenEzvizUser(Pointer pLoginInfo, Pointer pDeviceInfo);
	//int NET_DVR_CreateOpenEzvizUser(NET_DVR_OPEN_EZVIZ_USER_LOGIN_INFO pLoginInfo, NET_DVR_DEVICEINFO_V40 pDeviceInfo);
	boolean NET_DVR_DeleteOpenEzvizUser(int iUserID);

	boolean NET_DVR_GetInputSignalList_V40(int lUserID, int dwDevNum, NET_DVR_INPUT_SIGNAL_LIST lpInputSignalList);

//	int NET_DVR_RealPlay_V40(int lUserID,NET_DVR_PREVIEWINFO lpPreviewInfo,FRealDataCallBack_V30 fRealDataCall,Pointer pUser );
//	 boolean  NET_DVR_StopRealPlay(int lRealHandle);

	boolean NET_DVR_OpenSound(int lRealHandle);
	boolean NET_DVR_CloseSound();
	boolean NET_DVR_Volume(int lRealHandle, short wVolume);


	public interface FMSGCallBack extends Callback {
		public void invoke(int lCommand, NET_DVR_ALARMER pAlarmer,
						   Pointer pAlarmInfo, int dwBufLen, Pointer pUser);
	}

	public interface fRemoteConfigCallback extends Callback {
		public void invoke(int dwType, Pointer lpBuffer, int dwBufLen,
						   Pointer pUserData);
	}

	public interface FLoginResultCallBack extends Callback{
    	public int invoke(int lUserID, int dwResult, NET_DVR_DEVICEINFO_V30 lpDeviceinfo, Pointer pUser);
    }

    int NET_DVR_FindPicture(int lUserID, Pointer pFindParam);
    int NET_DVR_FindNextPicture_V40(int lFindHandle, Pointer lpFindData);
    boolean NET_DVR_CloseFindPicture(int lFindHandle);
    boolean NET_DVR_GetPicture_V30(int lUserID, Pointer sDVRFileName, Pointer sSavedFileBuf, int dwBufLen, IntByReference lpdwRetLen);

//	public interface FRealDataCallBack_V30 extends Callback{
//		public void invoke(int lRealHandle, int dwDataType,
//                ByteByReference pBuffer, int dwBufSize, Pointer pUser);
//    }

    int NET_DVR_GetRealPlayerIndex(int lRealHandle);
    int NET_DVR_GetPlayBackPlayerIndex(int lPlayHandle);

    boolean NET_DVR_SetCapturePictureMode(int dwCaptureMode);
    boolean NET_DVR_CapturePictureBlock(int iRealHandle, String sPicFileName, int dwTimeOut);
    boolean NET_DVR_CapturePicture(int iRealHandle, String sPicFileName);
    boolean NET_DVR_PlayBackCaptureFile(int lPlayHandle, String sFileName);

    boolean NET_DVR_SaveRealData_V30(int lRealHandle, int dwTransType, String sFileName);

    boolean NET_DVR_GetDeviceStatus(int lUserID, int dwCommand, int dwCount, Pointer lpInBuffer, int dwInBufferSize, Pointer lpStatusList, Pointer lpOutBuffer, int dwOutBufferSize);
    int NET_DVR_StartDownload(int lUserID, int dwDownloadType, Pointer lpInBuffer, int dwInBufferSize, String sFileName);
    int NET_DVR_GetDownloadState(int lUploadHandle, IntByReference pProgress);
    boolean NET_DVR_StopDownload(int lHandle);

    boolean NET_DVR_SetSDKLocalCfg(int enumType, Pointer lpInBuff);

	boolean NET_DVR_SetLogToFile(int nLogLevel, String strLogDir, boolean bAutoDel);

    boolean NET_DVR_MatrixGetSubSystemInfo_V40(int lUserID, NET_DVR_ALLSUBSYSTEMINFO_V40 netDvrAllsubsysteminfoV40);

    boolean NET_DVR_ManualSnap(int lUserID, Pointer lpInter,Pointer lpOuter);

    boolean NET_DVR_PlayBackControl_V40(int lPlayHandle, int dwControlCode, Pointer lpInBuffer,int dwInLen,Pointer  lpOutBuffer,Pointer lpOutLen);

    boolean NET_DVR_GetPicture_V50(int lUserID,  Pointer lpPicParam);
    
    int NET_DVR_StartListen_V30(Pointer  sLocalIP,short wLocalPort, FMSGCallBack DataCallback, Pointer  pUserData);
    
    boolean NET_DVR_StopListen_V30(int   lListenHandle);
	
	boolean NET_DVR_CaptureJPEGPicture_WithAppendData(int lUserID, int lChannel, Pointer JpegWithAppend);
}
