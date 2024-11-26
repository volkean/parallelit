package com.volkean.parallelit.dto;

import lombok.Data;

import java.util.List;

@Data
public class IpInfoResponse {
    private String ip;
    private String type;
    private String continentCode;
    private String continentName;
    private String countryCode;
    private String countryName;
    private String regionCode;
    private String regionName;
    private String city;
    private String zip;
    private Double latitude;
    private Double longitude;
    private String msa;
    private String dma;
    private String radius;
    private String ipRoutingType;
    private String connectionType;
    private Location location;

    public static class Location {
        private Integer geonameId;
        private String capital;
        private List<Language> languages;
        private String countryFlag;
        private String countryFlagEmoji;
        private String countryFlagEmojiUnicode;
        private String callingCode;
        private Boolean isEu;

        public static class Language {
            private String code;
            private String name;
            private String nativeName;

        }
    }
}
