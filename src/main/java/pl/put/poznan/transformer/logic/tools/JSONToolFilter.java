package pl.put.poznan.transformer.logic.tools;

/**
 * The type Json tool filter.
 */
public abstract class JSONToolFilter extends JSONToolDecorator {
    /**
     * The Filter list.
     */
    protected String[] filterList = null;

    /**
     * Instantiates a new Json tool filter.
     *
     * @param filterList the filter list
     */
    public JSONToolFilter(String[] filterList) {
        this.filterList = filterList;
    }

    /**
     * Instantiates a new Json tool filter.
     *
     * @param tool       the tool
     * @param filterList the filter list
     */
    public JSONToolFilter(IJSONTool tool, String[] filterList) {
        super(tool);
        this.filterList = filterList;
    }
}
