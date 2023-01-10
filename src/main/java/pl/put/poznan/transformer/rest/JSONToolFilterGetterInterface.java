package pl.put.poznan.transformer.rest;

import pl.put.poznan.transformer.logic.tools.JSONToolFilter;

/**
 * Helper interface to construct JSONToolFilter
 */
interface JSONToolFilterGetterInterface {

    /**
     * Construct JSONToolFilter using keys parameter
     *
     * @param keys extracted keys from payload
     * @return instantiates a new JSONToolFilter
     */
    JSONToolFilter get(String[] keys);
}