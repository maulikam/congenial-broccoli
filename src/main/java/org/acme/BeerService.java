package org.acme;

import java.util.concurrent.CompletionStage;

public interface BeerService {

    /**
     * @return
     */
    CompletionStage<Beer> getFromDraft();

}
