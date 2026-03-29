package com.example.resume.homepage.dto;

/**
 * 首页可编辑内容载体 / Home page editable payload
 */
public class HomePagePayload {

    private Hero hero;
    private About about;
    private Contact contact;
    private Footer footer;
    private SkillsTools skillsTools;
    private PetGallery petGallery;

    public Hero getHero() { return hero; }
    public void setHero(Hero hero) { this.hero = hero; }

    public About getAbout() { return about; }
    public void setAbout(About about) { this.about = about; }

    public Contact getContact() { return contact; }
    public void setContact(Contact contact) { this.contact = contact; }

    public Footer getFooter() { return footer; }
    public void setFooter(Footer footer) { this.footer = footer; }

    public SkillsTools getSkillsTools() { return skillsTools; }
    public void setSkillsTools(SkillsTools skillsTools) { this.skillsTools = skillsTools; }

    public PetGallery getPetGallery() { return petGallery; }
    public void setPetGallery(PetGallery petGallery) { this.petGallery = petGallery; }

    /** Hero 区内容 / Hero section */
    public static class Hero {
        private String fullName;
        private String title;
        private String backgroundUrl;
        private String typedItems;
        private String twitterUrl;
        private String facebookUrl;
        private String instagramUrl;
        private String linkedinUrl;

        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getBackgroundUrl() { return backgroundUrl; }
        public void setBackgroundUrl(String backgroundUrl) { this.backgroundUrl = backgroundUrl; }

        public String getTypedItems() { return typedItems; }
        public void setTypedItems(String typedItems) { this.typedItems = typedItems; }

        public String getTwitterUrl() { return twitterUrl; }
        public void setTwitterUrl(String twitterUrl) { this.twitterUrl = twitterUrl; }

        public String getFacebookUrl() { return facebookUrl; }
        public void setFacebookUrl(String facebookUrl) { this.facebookUrl = facebookUrl; }

        public String getInstagramUrl() { return instagramUrl; }
        public void setInstagramUrl(String instagramUrl) { this.instagramUrl = instagramUrl; }

        public String getLinkedinUrl() { return linkedinUrl; }
        public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }
    }

    /** About 区内容 / About section */
    public static class About {
        private String avatarUrl;
        private String bio;
        private java.util.List<String> highlights;

        public String getAvatarUrl() { return avatarUrl; }
        public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

        public String getBio() { return bio; }
        public void setBio(String bio) { this.bio = bio; }

        public java.util.List<String> getHighlights() { return highlights; }
        public void setHighlights(java.util.List<String> highlights) { this.highlights = highlights; }
    }

    /** Contact 区内容 / Contact section */
    public static class Contact {
        private String address;
        private String phone;
        private String email;

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

    /** Footer 区内容 / Footer section */
    public static class Footer {
        private String title;
        private String description;
        private String copyright;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getCopyright() { return copyright; }
        public void setCopyright(String copyright) { this.copyright = copyright; }
    }

    /** 技能与工具 / Skills & Tools section */
    public static class SkillsTools {
        private String sectionTitle;
        private String sectionSubtitle;
        private java.util.List<SkillGroup> groups;

        public String getSectionTitle() { return sectionTitle; }
        public void setSectionTitle(String sectionTitle) { this.sectionTitle = sectionTitle; }

        public String getSectionSubtitle() { return sectionSubtitle; }
        public void setSectionSubtitle(String sectionSubtitle) { this.sectionSubtitle = sectionSubtitle; }

        public java.util.List<SkillGroup> getGroups() { return groups; }
        public void setGroups(java.util.List<SkillGroup> groups) { this.groups = groups; }
    }

    /** 单个分组 / Single skill group */
    public static class SkillGroup {
        private String title;
        private String description;
        private String badge;
        private String ctaText;
        private String ctaHref;
        private java.util.List<String> items;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getBadge() { return badge; }
        public void setBadge(String badge) { this.badge = badge; }

        public String getCtaText() { return ctaText; }
        public void setCtaText(String ctaText) { this.ctaText = ctaText; }

        public String getCtaHref() { return ctaHref; }
        public void setCtaHref(String ctaHref) { this.ctaHref = ctaHref; }

        public java.util.List<String> getItems() { return items; }
        public void setItems(java.util.List<String> items) { this.items = items; }
    }

    /** Pet Gallery / 评价轮播内容 */
    public static class PetGallery {
        private String sectionTitle;
        private String sectionSubtitle;
        private java.util.List<PetItem> items;

        public String getSectionTitle() { return sectionTitle; }
        public void setSectionTitle(String sectionTitle) { this.sectionTitle = sectionTitle; }

        public String getSectionSubtitle() { return sectionSubtitle; }
        public void setSectionSubtitle(String sectionSubtitle) { this.sectionSubtitle = sectionSubtitle; }

        public java.util.List<PetItem> getItems() { return items; }
        public void setItems(java.util.List<PetItem> items) { this.items = items; }
    }

    /** Pet Gallery 单条内容 */
    public static class PetItem {
        private String name;
        private String role;
        private String comment;
        private String imageUrl;
        private Integer rating;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }

        public String getComment() { return comment; }
        public void setComment(String comment) { this.comment = comment; }

        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

        public Integer getRating() { return rating; }
        public void setRating(Integer rating) { this.rating = rating; }
    }
}
