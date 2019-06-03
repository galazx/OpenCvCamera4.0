LOCAL_PATH := $(call my-dir)

	include $(CLEAR_VARS)

	#opencv
	OPENCVROOT:= C:\Users\Maricondi\AndroidStudioProjects\opencv-3.4.2-android-sdk\OpenCV-android-sdk
	OPENCV_CAMERA_MODULES:=on
	OPENCV_INSTALL_MODULES:=on
	OPENCV_LIB_TYPE:=SHARED
	include ${OPENCVROOT}/sdk/native/jni/OpenCV.mk

	LOCAL_SRC_FILES := br_edu_ifspsaocarlos_sdm_opencvcamera4_OpencvClass.cpp

	LOCAL_LDLIBS += -llog
	LOCAL_MODULE := facelibs


	include $(BUILD_SHARED_LIBRARY)