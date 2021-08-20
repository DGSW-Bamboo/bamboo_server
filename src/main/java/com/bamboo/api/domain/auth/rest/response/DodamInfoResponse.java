package com.bamboo.api.domain.auth.rest.response;

import com.bamboo.api.global.config.restTemplate.response.BaseTemplate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DodamInfoResponse extends BaseTemplate {

  private final DodamInfoData data;

  public DodamInfoResponse (int status, String message, DodamInfoData data) {
    super(status, message);
    this.data = new DodamInfoData(data);
  }

  @Getter
  @AllArgsConstructor
  public static class DodamInfoData {

    private final String uniqueId;
    private final int grade;
    private final int room;
    private final int number;
    private final String name;
    private final String email;
    private final String profileImage;
    private final int accessLevel;

    public DodamInfoData (DodamInfoData data) {
      this.uniqueId = data.getUniqueId();
      this.grade = data.getGrade();
      this.room = data.getRoom();
      this.number = data.getNumber();
      this.name = data.getName();
      this.email = data.getEmail();
      this.profileImage = data.getProfileImage();
      this.accessLevel = data.getAccessLevel();
    }
  }
}
