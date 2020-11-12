package com.ffu.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ffu.web.rest.TestUtil;

public class InstagInfluencerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstagInfluencer.class);
        InstagInfluencer instagInfluencer1 = new InstagInfluencer();
        instagInfluencer1.setId(1L);
        InstagInfluencer instagInfluencer2 = new InstagInfluencer();
        instagInfluencer2.setId(instagInfluencer1.getId());
        assertThat(instagInfluencer1).isEqualTo(instagInfluencer2);
        instagInfluencer2.setId(2L);
        assertThat(instagInfluencer1).isNotEqualTo(instagInfluencer2);
        instagInfluencer1.setId(null);
        assertThat(instagInfluencer1).isNotEqualTo(instagInfluencer2);
    }
}
