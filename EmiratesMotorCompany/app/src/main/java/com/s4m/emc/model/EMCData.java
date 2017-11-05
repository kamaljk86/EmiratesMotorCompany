package com.s4m.emc.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EMCData {

    @SerializedName("ANNOUNCEMENT_IMAGE")
    @Expose
    private AnnouncementImage announcementImage;

    @SerializedName("ANNOUNCEMENT_TITLE")
    @Expose
    private AnnouncementTitle announcementTitle;

    @SerializedName("ANNOUNCEMENT_HTML")
    @Expose
    private AnnouncementDescription announcementDescription;

    public AnnouncementTitle getAnnouncementTitle() {
        return announcementTitle;
    }
    public void setAnnouncementTitle(AnnouncementTitle announcementTitle) {
        this.announcementTitle = announcementTitle;
    }

    public AnnouncementDescription getAnnouncementDescription() {
        return announcementDescription;
    }
    public void setAnnouncementTitle(AnnouncementDescription announcementDescription) {
        this.announcementDescription = announcementDescription;
    }

    public AnnouncementImage getAnnouncementImage() {
        return announcementImage;
    }
    public void setAnnouncementImage(AnnouncementImage announcementImage) {
        this.announcementImage = announcementImage;
    }

    public class AnnouncementTitle {

        @SerializedName("Value")
        @Expose
        private String titleValue;

        public String getTitleValue() {
            return titleValue;
        }
        public void setTitleValue(String titleValue) {
            this.titleValue = titleValue;
        }
    }

    public class AnnouncementDescription {

        @SerializedName("Value")
        @Expose
        private String descriptionValue;

        public String getDescriptionValue() {
            return descriptionValue;
        }
        public void setDescriptionValue(String descriptionValue) {
            this.descriptionValue = descriptionValue;
        }
    }

    public class AnnouncementImage {

        @SerializedName("Value")
        @Expose
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}