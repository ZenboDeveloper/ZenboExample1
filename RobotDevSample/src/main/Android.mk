LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_STATIC_JAVA_LIBRARIES := android-support-v13
LOCAL_STATIC_JAVA_LIBRARIES += android-support-v7-appcompat
LOCAL_STATIC_JAVA_LIBRARIES += ZenboSDK

LOCAL_SRC_FILES := $(call all-java-files-under, java)

robotactivity_dir := ../../../RobotActivityLibrary/
LOCAL_SRC_FILES += $(call all-java-files-under, $(robotactivity_dir)/src/main)

LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
LOCAL_RESOURCE_DIR += prebuilts/sdk/current/support/v7/appcompat/res

LOCAL_PACKAGE_NAME := RobotDevSample
#LOCAL_SDK_VERSION := current

LOCAL_AAPT_FLAGS := --auto-add-overlay
LOCAL_AAPT_FLAGS += --extra-packages android.support.v7.appcompat

#include $(LOCAL_PATH)/../../../version.mk
#LOCAL_AAPT_FLAGS += --version-code $(VERSION_CODE) --version-name $(VERSION_NAME)

LOCAL_PROGUARD_FLAG_FILES := ../../proguard-rules.pro

include $(BUILD_PACKAGE)
