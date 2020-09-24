package com.ffu.domain;

import com.ffu.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SocialNetworkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocialNetwork.class);
        SocialNetwork socialNetwork1 = new SocialNetwork();
        socialNetwork1.setId(1L);
        SocialNetwork socialNetwork2 = new SocialNetwork();
        socialNetwork2.setId(socialNetwork1.getId());
        assertThat(socialNetwork1).isEqualTo(socialNetwork2);
        socialNetwork2.setId(2L);
        assertThat(socialNetwork1).isNotEqualTo(socialNetwork2);
        socialNetwork1.setId(null);
        assertThat(socialNetwork1).isNotEqualTo(socialNetwork2);
    }
}
