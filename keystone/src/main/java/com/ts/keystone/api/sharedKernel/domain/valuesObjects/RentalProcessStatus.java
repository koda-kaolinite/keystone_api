package com.ts.keystone.api.sharedKernel.domain.valuesObjects;

public enum RentalProcessStatus {
    // Property Status (general)
    PROPERTY_NEW_FOR_ANALYSIS,    // New property registered, awaiting platform validation
    PROPERTY_APPROVED,            // Property validated and ready to be listed
    PROPERTY_REJECTED,            // Property does not meet requirements
    AVAILABLE,                    // Property listed and open for proposals
    RESERVED,                     // Property with proposal in final analysis phase
    RENTED,                       // Property with active lease agreement
    IN_MAINTENANCE,               // Property temporarily unavailable

    // Rental Journey Status (specific to a tenant/proposal)
    VISIT_REQUESTED,              // Initial interest, awaiting scheduling
    VISIT_SCHEDULED,              // Visit confirmed

    PROPOSAL_RECEIVED,            // Rental proposal formally submitted by the interested party
    PROPOSAL_IN_ANALYSIS,         // General analysis of the proposal (documents, credit)

    DOCUMENT_PENDING,             // Awaiting the first document submission
    DOCUMENT_IN_ANALYSIS,         // Documents received and being validated
    DOCUMENT_WITH_PENDENCY,       // Documents need correction or complement
    DOCUMENT_APPROVED,            // Tenant/guarantor documentation is OK
    DOCUMENT_REJECTED,            // Documentation not accepted

    PROPOSAL_APPROVED,            // Credit and document analysis successfully completed
    PROPOSAL_REJECTED,            // Proposal refused (credit, documents, etc.)

    // Contract and Finalization Phase
    AWAITING_CONTRACT_DRAFTING,
    AWAITING_SIGNATURES,
    CONTRACT_SIGNED,
    AWAITING_INITIAL_PAYMENT,
    PAYMENT_CONFIRMED,
    AWAITING_MOVE_IN_INSPECTION,
    KEYS_DELIVERED,               // End of process, start of rental

    // Cancellation/Termination Status
    PROCESS_CANCELED_BY_TENANT,   // The interested party gave up
    PROCESS_CANCELED_BY_REAL_ESTATE_AGENCY // The real estate agency or owner gave up
}