package com.ffu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ffu.web.rest.TestUtil;

public class SocialNetworkLinkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocialNetworkLink.class);
        SocialNetworkLink socialNetworkLink1 = new SocialNetworkLink();
        socialNetworkLink1.setId(1L);
        SocialNetworkLink socialNetworkLink2 = new SocialNetworkLink();
        socialNetworkLink2.setId(socialNetworkLink1.getId());
        assertThat(socialNetworkLink1).isEqualTo(socialNetworkLink2);
        socialNetworkLink2.setId(2L);
        assertThat(socialNetworkLink1).isNotEqualTo(socialNetworkLink2);
        socialNetworkLink1.setId(null);
        assertThat(socialNetworkLink1).isNotEqualTo(socialNetworkLink2);
    }
}
