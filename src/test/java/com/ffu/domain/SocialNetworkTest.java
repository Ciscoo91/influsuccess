package com.ffu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ffu.web.rest.TestUtil;

public class SocialNetworkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocialNetwork.class);
        SocialNetwork socialNetwork1 = new SocialNetwork();
       socialNetwork1.setName("1L");
        SocialNetwork socialNetwork2 = new SocialNetwork();
        socialNetwork2.setName(socialNetwork1.getName());
        assertThat(socialNetwork1).isEqualTo(socialNetwork2);
        socialNetwork2.setName("2L");
        assertThat(socialNetwork1).isNotEqualTo(socialNetwork2);
       socialNetwork1.setName(null);
        assertThat(socialNetwork1).isNotEqualTo(socialNetwork2);
    }
}
