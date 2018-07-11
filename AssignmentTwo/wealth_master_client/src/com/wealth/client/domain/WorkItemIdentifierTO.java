package com.wealth.client.domain;

/**
 * This TO contains the data that will allow the Process Engine to locate the desired Work Item.
 * 
 * @author sbing
 *
 */
public class WorkItemIdentifierTO extends BaseDomainEntityTO {

    private static final long serialVersionUID = 1L;

    private String caseReference;
    private String workQueueId;
    private String workItemTag;
    
    public WorkItemIdentifierTO() {
        caseReference = "";
        workQueueId = "";
        workItemTag = "";
    }

    public String getCaseReference() {
        return caseReference;
    }

    public void setCaseReference(String caseReference) {
        this.caseReference = caseReference;
    }

    public String getWorkQueueId() {
        return workQueueId;
    }

    public void setWorkQueueId(String workQueueId) {
        this.workQueueId = workQueueId;
    }

    public String getWorkItemTag() {
        return workItemTag;
    }

    public void setWorkItemTag(String workItemTag) {
        this.workItemTag = workItemTag;
    }
    
}
