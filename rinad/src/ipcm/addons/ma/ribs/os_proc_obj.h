/**
 * @file ribdv1.h
 * @author Bernat Gaston <bernat (dot) gaston (at) i2cat (dot) net>
 *
 * @brief Management Agent RIB daemon v1 - OSApplicationProcess
 */


#ifndef __RINAD_OS_PROC_OBJ_H__
#define __RINAD_OS_PROC_OBJ_H__

#include <librina/rib_v2.h>
#include <librina/patterns.h>
#include <librina/common.h>

namespace rinad{
namespace mad{
namespace rib_v1{

/**
 * OSApplicationProcess object
 */
class OSApplicationProcessObj : public rina::rib::EmptyRIBObject{

public:
	OSApplicationProcessObj(std::string name, long instance,
					rina::rib::RIBDNorthInterface* ribd);
	rina::cdap_rib::res_info_t* remoteCreate(const std::string& name,
			const std::string clas,
			const rina::cdap_rib::SerializedObject &obj_req,
			rina::cdap_rib::SerializedObject &obj_reply);
private:
	rina::rib::RIBDNorthInterface* ribd_;
	rina::rib::EmptyEncoder encoder_;

	//Name of the class
	const static std::string class_name;
};

}; //namespace rib_v1
}; //namespace mad
}; //namespace rinad


#endif  /* __RINAD_OS_PROC_OBJ_H__ */
