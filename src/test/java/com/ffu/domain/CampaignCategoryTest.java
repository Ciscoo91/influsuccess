package com.ffu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ffu.web.rest.TestUtil;

public class CampaignCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignCategory.class);
        CampaignCategory campaignCategory1 = new CampaignCategory();
        campaignCategory1.setName("1L");
        CampaignCategory campaignCategory2 = new CampaignCategory();
        campaignCategory2.setName(campaignCategory1.getName());
        assertThat(campaignCategory1).isEqualTo(campaignCategory2);
        campaignCategory2.setName("2L");
        assertThat(campaignCategory1).isNotEqualTo(campaignCategory2);
        campaignCategory1.setName(null);
        assertThat(campaignCategory1).isNotEqualTo(campaignCategory2);
    }
}
