package com.wgabbriel.budgetapi.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MoneyNormalizerTest {

  @Test
  void shouldConvertSmallValuesToCentsBeforePersisting() {
    assertThat(MoneyNormalizer.normalize(200L)).isEqualTo(20000L);
  }

  @Test
  void shouldKeepValuesAlreadyInCents() {
    assertThat(MoneyNormalizer.normalize(20000L)).isEqualTo(20000L);
  }
}
