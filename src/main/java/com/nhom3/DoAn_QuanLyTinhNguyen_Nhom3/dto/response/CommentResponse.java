package com.nhom3.DoAn_QuanLyTinhNguyen_Nhom3.dto.response;

import com.nhom3.DoAn_QuanLyTinhNguyen_Nhom3.entity.ActivityComment;

import java.time.LocalDateTime;

public class CommentResponse {
    private Long id;
    private Long activityId;
    private Long studentId;
    private String studentName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentResponse() {}

    public static CommentResponse from(ActivityComment comment) {
        CommentResponse r = new CommentResponse();
        r.id = comment.getId();
        r.activityId = comment.getActivity().getId();
        r.studentId = comment.getStudent().getId();
        r.studentName = comment.getStudent().getFullName();
        r.content = comment.getContent();
        r.createdAt = comment.getCreatedAt();
        r.updatedAt = comment.getUpdatedAt();
        return r;
    }

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
