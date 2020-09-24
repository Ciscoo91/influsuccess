package com.ffu.domain;

import com.ffu.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CampaignCategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignCategory.class);
        CampaignCategory campaignCategory1 = new CampaignCategory();
        campaignCategory1.setId(1L);
        CampaignCategory campaignCategory2 = new CampaignCategory();
        campaignCategory2.setId(campaignCategory1.getId());
        assertThat(campaignCategory1).isEqualTo(campaignCategory2);
        campaignCategory2.setId(2L);
        assertThat(campaignCategory1).isNotEqualTo(campaignCategory2);
        campaignCategory1.setId(null);
        assertThat(campaignCategory1).isNotEqualTo(campaignCategory2);
    }
}
