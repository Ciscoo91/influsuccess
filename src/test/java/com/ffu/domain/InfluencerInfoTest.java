package com.ffu.domain;

import com.ffu.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InfluencerInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InfluencerInfo.class);
        InfluencerInfo influencerInfo1 = new InfluencerInfo();
        influencerInfo1.setId(1L);
        InfluencerInfo influencerInfo2 = new InfluencerInfo();
        influencerInfo2.setId(influencerInfo1.getId());
        assertThat(influencerInfo1).isEqualTo(influencerInfo2);
        influencerInfo2.setId(2L);
        assertThat(influencerInfo1).isNotEqualTo(influencerInfo2);
        influencerInfo1.setId(null);
        assertThat(influencerInfo1).isNotEqualTo(influencerInfo2);
    }
}
