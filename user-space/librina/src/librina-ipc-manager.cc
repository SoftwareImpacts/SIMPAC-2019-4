/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

#define RINA_PREFIX "ipc-manager"

#include "logs.h"
#include "librina-ipc-manager.h"
#include "core.h"

namespace rina{

/* CLASS IPC PROCESS*/
const std::string IPCProcess::error_assigning_to_dif =
		"Error assigning IPC Process to DIF";
const std::string IPCProcess::error_registering_app =
		"Error registering application";
const std::string IPCProcess::error_not_a_dif_member =
		"Error: the IPC Process is not member of a DIF";
const std::string IPCProcess::error_allocating_flow =
		"Error allocating flow";

IPCProcess::IPCProcess() {
	id = 0;
	portId = 0;
	type = DIF_TYPE_NORMAL;
	difMember = false;
}

IPCProcess::IPCProcess(unsigned short id, unsigned int portId, DIFType type,
		const ApplicationProcessNamingInformation& name) {
	this->id = id;
	this->portId = portId;
	this->type = type;
	this->name = name;
	difMember = false;
}

bool IPCProcess::isDIFMember() const{
	return difMember;
}

void IPCProcess::setDIFMember(bool difMember){
	this->difMember = difMember;
}

unsigned int IPCProcess::getId() const {
	return id;
}

DIFType IPCProcess::getType() const {
	return type;
}

const ApplicationProcessNamingInformation& IPCProcess::getName() const {
	return name;
}

unsigned int IPCProcess::getPortId() const{
	return portId;
}

void IPCProcess::setPortId(unsigned int portId){
	this->portId = portId;
}

const DIFConfiguration& IPCProcess::getConfiguration() const{
	return difConfiguration;
}

void IPCProcess::setConfiguration(const DIFConfiguration& difConfiguration){
	this->difConfiguration = difConfiguration;
}

void IPCProcess::assignToDIF(
		const DIFConfiguration& difConfiguration) throw (IPCException) {
	LOG_DBG("IPCProcess::assign to DIF called");
#if STUB_API
	//Do nothing
#else
	IpcmAssignToDIFRequestMessage message;
	message.setDIFConfiguration(difConfiguration);
	message.setDestIpcProcessId(id);
	message.setDestPortId(portId);
	message.setRequestMessage(true);

	IpcmAssignToDIFResponseMessage * assignToDIFResponse =
			dynamic_cast<IpcmAssignToDIFResponseMessage *>(
					rinaManager->sendRequestAndWaitForResponse(&message,
							IPCProcess::error_assigning_to_dif));

	if (assignToDIFResponse->getResult() < 0){
		std::string reason = IPCProcess::error_assigning_to_dif + " " +
				assignToDIFResponse->getErrorDescription();
		delete assignToDIFResponse;
		throw IPCException(reason);
	}

	LOG_DBG("Assigned IPC Process %d to DIF %s", id,
			difConfiguration.getDifName().getProcessName().c_str());
	delete assignToDIFResponse;
#endif

	this->difConfiguration = difConfiguration;
	this->difMember = true;
}

void IPCProcess::notifyRegistrationToSupportingDIF(
		const ApplicationProcessNamingInformation& ipcProcessName,
		const ApplicationProcessNamingInformation& difName)
		throw (IPCException) {
	LOG_DBG("IPCProcess::notify registration to supporting DIF called");
#if STUB_API
	//Do nothing
#else
	IpcmDIFRegistrationNotification message;
	message.setIpcProcessName(ipcProcessName);
	message.setDestIpcProcessId(id);
	message.setDestPortId(portId);
	message.setDifName(difName);
	message.setRegistered(true);
	message.setNotificationMessage(true);

	try{
		rinaManager->sendResponseOrNotficationMessage(&message);
	}catch(NetlinkException &e){
		throw IPCException(e.what());
	}
#endif
}

void IPCProcess::notifyUnregistrationFromSupportingDIF(
		const ApplicationProcessNamingInformation& ipcProcessName,
		const ApplicationProcessNamingInformation& difName)
		throw (IPCException) {
	LOG_DBG("IPCProcess::notify unregistration from supporting DIF called");
#if STUB_API
	//Do nothing
#else
	IpcmDIFRegistrationNotification message;
	message.setIpcProcessName(ipcProcessName);
	message.setDestIpcProcessId(id);
	message.setDestPortId(portId);
	message.setDifName(difName);
	message.setRegistered(false);
	message.setNotificationMessage(true);

	try{
		rinaManager->sendResponseOrNotficationMessage(&message);
	}catch(NetlinkException &e){
		throw IPCException(e.what());
	}
#endif
}

void IPCProcess::enroll(const ApplicationProcessNamingInformation& difName,
		const ApplicationProcessNamingInformation& supportinDifName)
				throw (IPCException) {
	LOG_DBG("IPCProcess::enroll called");
	throw IPCException(IPCException::operation_not_implemented_error);
}

void IPCProcess::disconnectFromNeighbor(
		const ApplicationProcessNamingInformation& neighbor)
				throw (IPCException) {
	LOG_DBG("IPCProcess::disconnect from neighbour called");
	throw IPCException(IPCException::operation_not_implemented_error);
}

void IPCProcess::registerApplication(
		const ApplicationProcessNamingInformation& applicationName,
		unsigned int applicationPortId)
				throw (IPCException) {
	LOG_DBG("IPCProcess::register application called");
	if (!difMember){
		throw IPCException(IPCProcess::error_not_a_dif_member);
	}
#if STUB_API
	//Do nothing
#else
	IpcmRegisterApplicationRequestMessage message;
	message.setApplicationName(applicationName);
	message.setDifName(difConfiguration.getDifName());
	message.setApplicationPortId(applicationPortId);
	message.setRequestMessage(true);

	IpcmRegisterApplicationResponseMessage * registerAppResponse =
		dynamic_cast<IpcmRegisterApplicationResponseMessage *>(
			rinaManager->sendRequestAndWaitForResponse(&message,
				IPCProcess::error_registering_app));

	if (registerAppResponse->getResult() < 0){
		std::string reason = IPCProcess::error_registering_app + " " +
				registerAppResponse->getErrorDescription();
		delete registerAppResponse;
		throw IPCException(reason);
	}

	LOG_DBG("Registered app %s to DIF %s",
			applicationName.getProcessName().c_str(),
			difConfiguration.getDifName().getProcessName().c_str());
	delete registerAppResponse;
#endif
}

void IPCProcess::unregisterApplication(
		const ApplicationProcessNamingInformation& applicationName)
				throw (IPCException) {
	LOG_DBG("IPCProcess::unregister application called");
}

void IPCProcess::allocateFlow(const FlowRequestEvent& flowRequest,
		unsigned int applicationPortId) throw (IPCException) {
	LOG_DBG("IPCProcess::allocate flow called");
	if (!difMember){
		throw IPCException(IPCProcess::error_not_a_dif_member);
	}
#if STUB_API
	//Do nothing
#else
	IpcmAllocateFlowRequestMessage message;
	message.setSourceAppName(flowRequest.getSourceApplicationName());
	message.setDestAppName(flowRequest.getDestApplicationName());
	message.setFlowSpec(flowRequest.getFlowSpecification());
	message.setDifName(flowRequest.getDIFName());
	message.setPortId(flowRequest.getPortId());
	message.setApplicationPortId(applicationPortId);
	message.setRequestMessage(true);

	IpcmAllocateFlowResponseMessage * allocateFlowResponse =
		dynamic_cast<IpcmAllocateFlowResponseMessage *>(
			rinaManager->sendRequestAndWaitForResponse(&message,
				IPCProcess::error_allocating_flow));

	if (allocateFlowResponse->getResult() < 0){
		std::string reason = IPCProcess::error_allocating_flow + " " +
				allocateFlowResponse->getErrorDescription();
		delete allocateFlowResponse;
		throw IPCException(reason);
	}

	LOG_DBG("Allocated flow from %s to %s with portId %d",
			message.getSourceAppName().getProcessName().c_str(),
			message.getDestAppName().getProcessName().c_str(),
			message.getPortId());
	delete allocateFlowResponse;

#endif
}

void IPCProcess::queryRIB() {
	LOG_DBG("IPCProcess::query RIB called");
}

/** CLASS IPC PROCESS FACTORY */

const std::string IPCProcessFactory::unknown_ipc_process_error =
		"Could not find an IPC Process with the provided id";

IPCProcess * IPCProcessFactory::create(
		const ApplicationProcessNamingInformation& ipcProcessName,
		DIFType difType) throw (IPCException) {
	LOG_DBG("IPCProcessFactory::create called");

	int ipcProcessId = 1;
	for (int i = 1; i < 1000; i++) {
		if (ipcProcesses.find(i) == ipcProcesses.end()) {
			ipcProcessId = i;
			break;
		}
	}

	IPCProcess * ipcProcess = new IPCProcess(ipcProcessId, 0, difType,
			ipcProcessName);
	ipcProcesses[ipcProcessId] = ipcProcess;
	return ipcProcess;
}

void IPCProcessFactory::destroy(unsigned int ipcProcessId)
		throw (IPCException) {
	LOG_DBG("IPCProcessFactory::destroy called");

	std::map<int, IPCProcess*>::iterator iterator;
	iterator = ipcProcesses.find(ipcProcessId);
	if (iterator == ipcProcesses.end()) {
		throw IPCException(IPCProcessFactory::unknown_ipc_process_error);
	}

	delete iterator->second;
	ipcProcesses.erase(ipcProcessId);
}

std::vector<IPCProcess *> IPCProcessFactory::listIPCProcesses() {
	LOG_DBG("IPCProcessFactory::list IPC Processes called");
	std::vector<IPCProcess *> response;

	for (std::map<int, IPCProcess*>::iterator it = ipcProcesses.begin();
			it != ipcProcesses.end(); ++it) {
		response.push_back(it->second);
	}

	return response;
}

Singleton<IPCProcessFactory> ipcProcessFactory;

/** CLASS APPLICATION MANAGER */

void ApplicationManager::applicationRegistered(
		const ApplicationRegistrationRequestEvent& event,
		unsigned short ipcProcessId, int ipcProcessPortId, int result,
		const std::string& errorDescription) throw (IPCException) {
	LOG_DBG("ApplicationManager::applicationRegistered called");

#if STUB_API
	//Do nothing
#else
	AppRegisterApplicationResponseMessage responseMessage;
	responseMessage.setApplicationName(event.getApplicationName());
	responseMessage.setDifName(event.getDIFName());
	responseMessage.setIpcProcessId(ipcProcessId);
	responseMessage.setIpcProcessPortId(ipcProcessPortId);
	responseMessage.setResult(result);
	responseMessage.setErrorDescription(errorDescription);
	responseMessage.setSequenceNumber(event.getSequenceNumber());
	responseMessage.setResponseMessage(true);
	try{
		rinaManager->sendResponseOrNotficationMessage(&responseMessage);
	}catch(NetlinkException &e){
		throw IPCException(e.what());
	}
#endif
}

void ApplicationManager::applicationUnregistered(unsigned int transactionId,
		const std::string& response) throw (IPCException) {
	LOG_DBG("ApplicationManager::applicationUnregistered called");
}

void ApplicationManager::flowAllocated(const FlowRequestEvent flowRequestEvent,
		std::string errorDescription, unsigned short ipcProcessId,
		unsigned int ipcProcessPortId) throw (IPCException) {
	LOG_DBG("ApplicationManager::flowAllocated called");

#if STUB_API
	//Do nothing
#else
	AppAllocateFlowRequestResultMessage responseMessage;
	responseMessage.setPortId(flowRequestEvent.getPortId());
	responseMessage.setErrorDescription(errorDescription);
	responseMessage.setIpcProcessId(ipcProcessId);
	responseMessage.setIpcProcessPortId(ipcProcessPortId);
	responseMessage.setSourceAppName(flowRequestEvent.getSourceApplicationName());
	responseMessage.setDifName(flowRequestEvent.getDIFName());
	responseMessage.setSequenceNumber(flowRequestEvent.getSequenceNumber());
	responseMessage.setResponseMessage(true);
	try{
		rinaManager->sendResponseOrNotficationMessage(&responseMessage);
	}catch(NetlinkException &e){
		throw IPCException(e.what());
	}
#endif
}

Singleton<ApplicationManager> applicationManager;

}
